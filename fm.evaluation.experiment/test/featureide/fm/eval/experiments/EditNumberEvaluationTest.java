package featureide.fm.eval.experiments;

import org.junit.Test;

import featureide.fm.eval.EvaluationSpec.OperationType;

public class EditNumberEvaluationTest {
	@Test
	public void testAllAdd() {
		OperationNumberEvaluation evaluation = new OperationNumberEvaluation(
				OperationType.AllAdd);
		evaluation.doEvaluate();

	}

	@Test
	public void testAllRemove() {
		OperationNumberEvaluation evaluation = new OperationNumberEvaluation(
				OperationType.AllRemove);
		evaluation.doEvaluate();
	}

	@Test
	public void testAllSet() {
		OperationNumberEvaluation evaluation = new OperationNumberEvaluation(
				OperationType.AllSet);
		evaluation.doEvaluate();
	}

	@Test
	public void testRandom() {
		OperationNumberEvaluation evaluation = new OperationNumberEvaluation(
				OperationType.Random);
		evaluation.doEvaluate();
	}

}
