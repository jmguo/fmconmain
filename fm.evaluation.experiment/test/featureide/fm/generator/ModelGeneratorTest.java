package featureide.fm.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.sat4j.specs.TimeoutException;

import featureide.fm.eval.EvaluationModel;
import featureide.fm.eval.EvaluationModelFactory;
import featureide.fm.eval.EvaluationSpec;
import featureide.fm.eval.EvaluationSpec.ModelGenType;
import featureide.fm.eval.EvaluationSpec.OperationType;
import featureide.fm.io.guidsl.FeatureModelChecker;
import featureide.fm.io.guidsl.FeatureModelWriter;
import featureide.fm.model.EvolutionStrategy;
import featureide.fm.model.FeatureModel;
import featureide.fm.util.TimedWoker;

public class ModelGeneratorTest {

	final static int[] sizes = new int[] { 10, 50, 100, 200, 500, 1000, 2000,
			5000, 10000 };

	public static EvaluationSpec[] createSpecs() {
		EvaluationSpec[] specs = new EvaluationSpec[sizes.length];
		for (int i = 0; i < specs.length; i++) {
			EvaluationSpec spec = new EvaluationSpec();
			spec.setStartId(100);
			spec.setRepeateNum(200); 
			spec.setModelGenType(ModelGenType.CreateAndSave); //for generate FMs #20101108
			spec.setOperation(10);
			spec.setOperationType(OperationType.Random);
			spec.setModelDir("200\\");
			spec.setModelNum(sizes[i]);
			spec.setDoPostCheck(true);
			spec.setEvolutionStrategy(EvolutionStrategy.Random);

			specs[i] = spec;
		}
		return specs;
	}

	EvaluationModel evalModel;

	@Test
	public void testGenerateFeatureModel() {

		TimedWoker worker = new TimedWoker() {
			public void excute() {
				EvaluationSpec[] specs = createSpecs();
				for (int i = 0; i < specs.length; i++)
					evalModel = EvaluationModelFactory.generateModels(specs[i]);
			}
		};
		worker.run();
		System.out.println(worker.getStopWatch().getElapsedTimeString());

	}

	public void testIsGeneratedModelValid() {
		final EvaluationModel[] evalModels = new EvaluationModel[sizes.length];

		TimedWoker worker = new TimedWoker() {
			public void excute() {
				EvaluationSpec[] specs = createSpecs();
				for (int i = 0; i < specs.length; i++)
					evalModels[i] = EvaluationModelFactory
							.generateModels(specs[i]);
			}
		};
		worker.run();

		System.out.print("Generated Models in: ");
		System.out.println(worker.getStopWatch().getElapsedTimeString());

		System.out.println("Accuracy\nSAT\tGuidsl");

		List<FeatureModel> wrongSAT = new ArrayList<FeatureModel>();
		List<FeatureModel> wrongGuidsl = new ArrayList<FeatureModel>();
		for (EvaluationModel model : evalModels) {
			Collection<FeatureModel> col = model.getModels();
			int rightSAT = 0;
			int rightGuidsl = 0;
			int total = col.size();
			for (FeatureModel fm : col) {
				try {
					if (fm.isValid()) {
						rightSAT++;
					} else {
						wrongSAT.add(fm);
					}
				} catch (TimeoutException e) {
					e.printStackTrace();
				}

				if (FeatureModelChecker.guidslCheck(fm) == null) {
					rightGuidsl++;
				} else {
					wrongGuidsl.add(fm);
				}
			}
			double accuracySAT = 100.0 * rightSAT / total;
			double accuracyGuidsl = 100.0 * rightGuidsl / total;
			System.out.println(accuracySAT + "%\t" + accuracyGuidsl + "%");
		}

		FeatureModelWriter fw = new FeatureModelWriter();
		System.out.println("Wrong Models found by SAT");
		for (FeatureModel fm : wrongSAT) {
			fw.setFeatureModel(fm);
			System.out.println(fw.writeToString());
			System.out.println("--------------------------------------------");
		}

		System.out.println("Wrong Models found by Guidsl");
		for (FeatureModel fm : wrongGuidsl) {
			fw.setFeatureModel(fm);
			System.out.println(fw.writeToString());
			System.out.println("--------------------------------------------");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ModelGeneratorTest test = new ModelGeneratorTest();
		// test.testIsGeneratedModelValid();
		test.testGenerateFeatureModel();
	}
}
