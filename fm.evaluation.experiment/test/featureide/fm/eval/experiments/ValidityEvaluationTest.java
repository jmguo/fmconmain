package featureide.fm.eval.experiments;

import org.junit.Test;

import featureide.fm.eval.EvaluationSpec.OperationType;

public class ValidityEvaluationTest {
	@Test
	public void testEvaluate() {
		ValidityEvaluation fnEvaluation = new ValidityEvaluation(
				OperationType.Random);
		fnEvaluation.doEvaluate();
	}
}
