package eval.ui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import featureide.fm.eval.EvaluationReport;
import featureide.fm.model.EvolutionStrategy;

public class PlotChartFactory {

	/**
	 * For FeatureNumberEvaluation
	 * 
	 * @param reports
	 * @return
	 */
	public static CategoryDataset createFNDataset(EvaluationReport[] reports) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int index = 0; index < reports.length; index++) {
			EvaluationReport report = reports[index];
			String colKey = String.valueOf(report.getSpec().getModelNum());
			report.generate();
			// List<Double> times = report.getUsedTimes();
			// for (int i = 0; i < times.size(); i++) {
			// dataset.addValue(times.get(i) * 100, "S" + (i + 1), colKey);
			//
			// }
			dataset.addValue(report.getAverageTime(), "Our approach", colKey);
			dataset.addValue(report.getAvgGuidslTime(), "Guidsl", colKey);
			dataset.addValue(report.getAvgSatTime(), "Sat4j", colKey);
		}

		return dataset;
	}

	/**
	 * For EditKindsEvaluation
	 * 
	 * @param reports
	 * @return
	 */
	public static CategoryDataset createENDataset(EvaluationReport[][] reports) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int index = 0; index < reports.length; index++) {
			EvaluationReport[] report = reports[index];
			for (int i = 0; i < report.length; i++) {
				report[i].generate();
				String colKey = String.valueOf(report[i].getSpec()
						.getOperationNum());
				dataset.addValue(report[i].getAverageTime() * 100, report[i]
						.getSpec().getOperationType().toString(), colKey);
			}

		}

		return dataset;
	}

	/**
	 * For EditKindsEvaluation
	 * 
	 * @param reports
	 * @return
	 */
	public static CategoryDataset createEKDataset(EvaluationReport[][] reports) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int index = 0; index < reports.length; index++) {
			EvaluationReport[] report = reports[index];
			for (int i = 0; i < report.length; i++) {
				report[i].generate();
				String colKey = String.valueOf(report[i].getSpec()
						.getModelNum());
				dataset.addValue(report[i].getAverageTime() * 100, report[i]
						.getSpec().getOperationType().toString(), colKey);
			}

		}

		return dataset;
	}

	/**
	 * For EditKindsEvaluation
	 * 
	 * @param reports
	 * @return
	 */
	public static CategoryDataset createESDataset(EvaluationReport[][] reports) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int index = 0; index < reports.length; index++) {
			EvaluationReport[] report = reports[index];
			for (int i = 0; i < report.length; i++) {
				report[i].generate();
				String colKey = String.valueOf(report[i].getSpec()
						.getModelNum());
				EvolutionStrategy es = report[i].getSpec()
						.getEvolutionStrategy();
				switch (es) {
				case DeleteAllChildren:
					dataset.addValue(report[i].getAverageTime() * 100,
					/* "RemoveAllChildren" */es.toString(), colKey);
					break;
				case MergeChildrenToParent:
					dataset.addValue(report[i].getAverageTime() * 100,
					/* "ReconnectChildrenToParent" */es.toString(), colKey);
					break;
				case MergeChildToAnotherCompound:
					dataset.addValue(report[i].getAverageTime() * 100,
					/* "ReconnectChildrenToAnotherNF" */es.toString(), colKey);
					break;
				}

			}

		}

		return dataset;
	}

	public static JPanel createFNChartPanel(EvaluationReport[] reports) {
		// JFreeChart chart = createBoxPlotChart(createFNDataset(reports),
		// reports[0].getSpec().getRepeateNum());
		JFreeChart chart = createXYPlotChart(createFNDataset(reports),
				"Feature Number", "#features");
		return new ChartPanel(chart);
	}

	public static JPanel createEKChartPanel(EvaluationReport[][] reports) {
		JFreeChart chart = createXYPlotChart(createEKDataset(reports),
				"Operation Kinds", "#features");
		return new ChartPanel(chart);
	}

	public static JPanel createENChartPanel(EvaluationReport[][] reports) {
		JFreeChart chart = createXYPlotChart(createENDataset(reports),
				"Operation Number", "#operations");
		return new ChartPanel(chart);
	}

	public static JPanel createESChartPanel(EvaluationReport[][] reports) {
		JFreeChart chart = createXYPlotChart(createESDataset(reports),
				"Evolution Strategy", "#features");
		return new ChartPanel(chart);
	}

	private static JFreeChart createBoxPlotChart(CategoryDataset dataset,
			int repeateNum) {
		JFreeChart localJFreeChart = ChartFactory.createLineChart(
				"Feature Number", "#features", "Calculation Time(0.01ms)",
				dataset, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot localCategoryPlot = (CategoryPlot) localJFreeChart
				.getPlot();
		localCategoryPlot.setBackgroundPaint(Color.white);
		NumberAxis localNumberAxis = (NumberAxis) localCategoryPlot
				.getRangeAxis();
		localNumberAxis.setStandardTickUnits(NumberAxis
				.createIntegerTickUnits());
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) localCategoryPlot
				.getRenderer();

		Ellipse2D rect = new Ellipse2D.Double(0, 0, 4, 4);

		for (int i = 0; i < repeateNum; i++) {
			lineAndShapeRenderer.setSeriesShapesVisible(i, true);
			lineAndShapeRenderer.setSeriesLinesVisible(i, false);
			lineAndShapeRenderer.setSeriesPaint(i, Color.blue);
			lineAndShapeRenderer.setSeriesShape(i, rect);

		}
		lineAndShapeRenderer.setSeriesPaint(repeateNum, Color.red);
		lineAndShapeRenderer.setSeriesShapesVisible(repeateNum, true);
		lineAndShapeRenderer.setSeriesShape(repeateNum, new Rectangle(-6, -2,
				16, 5));

		lineAndShapeRenderer.setDrawOutlines(true);
		lineAndShapeRenderer.setUseFillPaint(true);
		lineAndShapeRenderer.setBaseFillPaint(Color.white);
		return localJFreeChart;
	}

	private static JFreeChart createXYPlotChart(CategoryDataset dataset,
			String title, String catogaryAxisLabel) {
		String yaxis = "Calculation Time(0.01ms)";
		if (title.contains("Feature Number")) {
			yaxis = "Calculation Time(ms)";
		}
		JFreeChart localJFreeChart = ChartFactory.createLineChart(title,
				catogaryAxisLabel, yaxis, dataset, PlotOrientation.VERTICAL,
				true, true, false);
		localJFreeChart.getLegend().setPosition(RectangleEdge.TOP);
		CategoryPlot localCategoryPlot = (CategoryPlot) localJFreeChart
				.getPlot();
		localCategoryPlot.setBackgroundPaint(Color.white);
		NumberAxis localNumberAxis = (NumberAxis) localCategoryPlot
				.getRangeAxis();
		// localNumberAxis.setRange(0, 500);
		localNumberAxis.setStandardTickUnits(NumberAxis
				.createIntegerTickUnits());

		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) localCategoryPlot
				.getRenderer();
		for (int i = 0; i < 4; i++) {
			lineAndShapeRenderer.setSeriesShapesVisible(i, true);
		}
		lineAndShapeRenderer.setDrawOutlines(true);
		lineAndShapeRenderer.setUseFillPaint(true);
		lineAndShapeRenderer.setBaseFillPaint(Color.white);
		return localJFreeChart;
	}
}
