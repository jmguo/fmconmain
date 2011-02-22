package featureide.fm.util;

import org.junit.Assert;
import org.junit.Test;

import featureide.fm.eval.EvaluationReport;
import featureide.fm.eval.FMEvaluationRunner;
import featureide.fm.eval.EvaluationSpec;
import featureide.fm.eval.EvaluationSpec.OperationType;
import featureide.fm.eval.EvaluationSpec.ModelGenType;
import featureide.fm.model.EvolutionStrategy;

public class XStreamUtilTest {

	public EvaluationSpec createSpec() {
		EvaluationSpec spec = new EvaluationSpec();
		spec.setStartId(100);
		spec.setRepeateNum(200);
		spec.setModelGenType(ModelGenType.CreateNotSave);
		spec.setOperation(10);
		spec.setOperationType(OperationType.Random);
		spec.setModelDir("models");
		spec.setModelNum(200);
		spec.setEvolutionStrategy(EvolutionStrategy.Random);

		return spec;
	}

	@Test
	public void testSave() {
		EvaluationReport report = FMEvaluationRunner.evaluate(createSpec());
		String nameA = report.getName();

		XStreamUtil.save(report);
		report = XStreamUtil.load(report.getName());

		Assert.assertEquals(nameA, report.getName());

		System.out.println("Report:\n" + report);
	}

	@Test
	public void testLoad() {
		// fail("Not yet implemented");
	}

}
