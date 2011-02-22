package eval.ui;

import org.jfree.ui.RefineryUtilities;

import featureide.fm.eval.Evaluation;
import featureide.fm.eval.EvaluationReport;

public class EvaluationReportViewer {

	public void viewReportsChart(EvaluationReport[] reports) {
		String title = "Evaluation Results";
		ChartFrame frame = new ChartFrame(title, reports);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
	}

	public void viewReportsChart(EvaluationReport[][] reports,
			Evaluation.EvaluationType evaluationType) {
		String title = "Evaluation Results";
		ChartFrame frame = new ChartFrame(title, reports, evaluationType);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
