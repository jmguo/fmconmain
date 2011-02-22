package eval.ui;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.ui.ApplicationFrame;

import featureide.fm.eval.Evaluation;
import featureide.fm.eval.EvaluationReport;

public class ChartFrame extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	public ChartFrame(String title, EvaluationReport[] reports) {
		super(title);
		JPanel localJPanel = PlotChartFactory.createFNChartPanel(reports);
		localJPanel.setPreferredSize(new Dimension(480, 320));
		setContentPane(localJPanel);
	}

	public ChartFrame(String title, EvaluationReport[][] reports,
			Evaluation.EvaluationType evaluationType) {
		super(title);
		JPanel localJPanel = null;
		if (evaluationType == Evaluation.EvaluationType.EditKinds) {
			localJPanel = PlotChartFactory.createEKChartPanel(reports);
		} else if (evaluationType == Evaluation.EvaluationType.EditNumber) {
			localJPanel = PlotChartFactory.createENChartPanel(reports);
		} else if (evaluationType == Evaluation.EvaluationType.ES) {
			localJPanel = PlotChartFactory.createESChartPanel(reports);
		}

		localJPanel.setPreferredSize(new Dimension(480, 320));
		setContentPane(localJPanel);
	}
}
