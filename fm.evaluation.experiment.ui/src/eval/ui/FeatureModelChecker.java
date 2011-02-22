package eval.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.sat4j.specs.TimeoutException;

import featureide.fm.io.UnsupportedModelException;
import featureide.fm.io.guidsl.FeatureModelReader;
import featureide.fm.model.FeatureModel;

public class FeatureModelChecker {
	private final Display display;
	private final Shell shell;

	public FeatureModelChecker() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new FormLayout());
		shell.setSize(800, 600);

		final Text text = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		Font sourceFont = new Font(display, "Courier New", 10, SWT.NORMAL);
		text.setFont(sourceFont);
		final FormData textData = new FormData();
		textData.left = new FormAttachment(0);
		textData.right = new FormAttachment(100);
		textData.top = new FormAttachment(0);
		textData.bottom = new FormAttachment(90);
		text.setLayoutData(textData);

		final Button button = new Button(shell, SWT.NONE);
		button.setText("Validate");
		final FormData btnData = new FormData();
		btnData.top = new FormAttachment(text, 10);
		btnData.bottom = new FormAttachment(98);
		btnData.left = new FormAttachment(2);
		btnData.right = new FormAttachment(20);
		button.setLayoutData(btnData);

		final Label msg = new Label(shell, SWT.WRAP);
		msg.setFont(sourceFont);
		msg.setForeground(display.getSystemColor(SWT.COLOR_RED));
		final FormData msgData = new FormData();
		msgData.left = new FormAttachment(button, 20);
		msgData.right = new FormAttachment(100);
		msgData.top = new FormAttachment(text, 20);
		msgData.bottom = new FormAttachment(100);
		msg.setLayoutData(msgData);
		msg.setText("Result: ");

		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean guidsl = true, sat = true;
				String failMsg = "";

				String content = text.getText();
				FeatureModel fm = new FeatureModel();
				FeatureModelReader fr = new FeatureModelReader(fm);
				try {
					fr.readFromString(content);
				} catch (UnsupportedModelException e2) {
					e2.printStackTrace();
					failMsg += "Guidsl: " + e2.getMessage() + "\n";
					guidsl = false;
					sat = false;
				}
				if (guidsl) {
					try {
						sat = fm.isValid();
					} catch (TimeoutException e1) {
						e1.printStackTrace();
						sat = false;
					}
				}

				String result = "Result:\tguidsl: " + guidsl + "\tsat: " + sat
						+ "\n" + failMsg;
				msg.setText(result);
				if (guidsl && sat) {
					msg.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				} else {
					msg.setForeground(display.getSystemColor(SWT.COLOR_RED));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		sourceFont.dispose();
		display.dispose();
	}

	public static void main(String[] args) {
		new FeatureModelChecker();
	}
}
