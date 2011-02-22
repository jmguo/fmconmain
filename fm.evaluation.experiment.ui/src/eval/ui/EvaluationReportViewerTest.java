package eval.ui;

import java.io.File;

import junit.framework.TestCase;
import featureide.fm.eval.Evaluation;
import featureide.fm.eval.EvaluationReport;
import featureide.fm.eval.Evaluation.EvaluationType;
import featureide.fm.util.XStreamUtil;

public class EvaluationReportViewerTest extends TestCase {

	public void viewENReportsChart() {
		File dir = new File(Evaluation.rootPath + EvaluationReport.dirPath
				+ "EditNumbers");

		File[] files = dir.listFiles();
		EvaluationReport[][] reportss = new EvaluationReport[4][];
		for (int i = 0; i < 4; i++) {
			reportss[i] = new EvaluationReport[12];
		}
		int a, b, c, d;
		a = b = c = d = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains("AllAdd"))
				reportss[0][a++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("AllRemove"))
				reportss[1][b++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("AllSet"))
				reportss[2][c++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("Random_"))
				reportss[3][d++] = XStreamUtil.load(files[i]);
		}

		EvaluationReportViewer viewer = new EvaluationReportViewer();
		viewer.viewReportsChart(reportss, EvaluationType.EditNumber);
		System.out.println("Edit Number Evaluation Reports");
		for (EvaluationReport[] reports : reportss) {
			System.out.println(reports[0].getSpec().getOperationNum());
			showEvalReports(reports);
		}
	}

	public void viewEKReportsChart() {
		File dir = new File(Evaluation.rootPath + EvaluationReport.dirPath
				+ "EditKinds");

		File[] files = dir.listFiles();
		EvaluationReport[][] reportss = new EvaluationReport[4][];
		for (int i = 0; i < 4; i++) {
			reportss[i] = new EvaluationReport[9];
		}
		int a, b, c, d;
		a = b = c = d = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains("AllAdd"))
				reportss[0][a++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("AllRemove"))
				reportss[1][b++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("AllSet"))
				reportss[2][c++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("OOXX"))
				reportss[3][d++] = XStreamUtil.load(files[i]);

		}
		EvaluationReportViewer viewer = new EvaluationReportViewer();
		viewer.viewReportsChart(reportss, EvaluationType.EditKinds);
		System.out.println("Edit Kinds Evaluation Reports");
		for (EvaluationReport[] reports : reportss) {
			System.out.println(reports[0].getSpec().getOperationType());
			showEvalReports(reports);
		}
	}

	public void viewESReportsChart() {
		File dir = new File(Evaluation.rootPath + EvaluationReport.dirPath
				+ "EvolutionStrategy");

		File[] files = dir.listFiles();
		EvaluationReport[][] reportss = new EvaluationReport[3][];
		for (int i = 0; i < 3; i++) {
			reportss[i] = new EvaluationReport[9];
		}
		int a, b, c;
		a = b = c = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains("DeleteAllChildren"))
				reportss[0][a++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("MergeChildrenToParent"))
				reportss[1][b++] = XStreamUtil.load(files[i]);
			if (files[i].getName().contains("MergeChildToAnotherCompound"))
				reportss[2][c++] = XStreamUtil.load(files[i]);

		}
		EvaluationReportViewer viewer = new EvaluationReportViewer();
		viewer.viewReportsChart(reportss, EvaluationType.ES);
		System.out.println("Evolution Strategy Evaluation Reports");
		for (EvaluationReport[] reports : reportss) {
			System.out.println(reports[0].getSpec().getEvolutionStrategy());
			showEvalReports(reports);
		}
	}

	public void viewFNReportsChart() {

		File dir = new File(Evaluation.rootPath + EvaluationReport.dirPath
				+ "FeatureNumbers");

		File[] files = dir.listFiles();
		EvaluationReport[] reports = new EvaluationReport[files.length];
		for (int i = 0; i < files.length; i++) {
			reports[i] = XStreamUtil.load(files[i]);
		}

		EvaluationReportViewer viewer = new EvaluationReportViewer();
		viewer.viewReportsChart(reports);
		System.out.println("Feature Number Evaluation Reports");
		showEvalReports(reports);
		// display validation result
		for (int i = 0; i < reports.length; i++) {
			int repeateNum = reports[i].getSpec().getRepeateNum();
			int guidsl = reports[i].getGuidslCheckFails().size();
			int sat = reports[i].getSatCheckFails().size();

			double gRate = (repeateNum - guidsl) * 100.0 / repeateNum;
			double sRate = (repeateNum - sat) * 100.0 / repeateNum;
			System.out.println(String.format("%d\t\t%.2f%%\t\t%.2f%%",
					reports[i].getSpec().getModelNum(), gRate, sRate));
		}
	}

	private void showEvalReports(EvaluationReport[] reports) {
		System.out.println(EvaluationReport.header);
		for (EvaluationReport report : reports) {
			System.out.println(report);
		}
		System.out.println();

	}

	public static void main(String[] args) {
		EvaluationReportViewerTest tc = new EvaluationReportViewerTest();
		tc.viewFNReportsChart();
		tc.viewENReportsChart();
		tc.viewEKReportsChart();
		tc.viewESReportsChart();
	}
}
