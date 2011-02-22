package featureide.fm.eval.experiments;

import org.junit.Test;

import featureide.fm.eval.EvaluationSpec.OperationType;

public class EditKindsEvaluationTest {

	@Test
	public void testAllAdd() {
		OperationKindsEvaluation evaluation = new OperationKindsEvaluation(
				OperationType.AllAdd);
		evaluation.doEvaluate();
	}

	@Test
	public void testAllRemove() {
		OperationKindsEvaluation evaluation = new OperationKindsEvaluation(
				OperationType.AllRemove);
		evaluation.doEvaluate();

	}

	@Test
	public void testAllSet() {
		OperationKindsEvaluation evaluation = new OperationKindsEvaluation(
				OperationType.AllSet);
		evaluation.doEvaluate();

	}

	@Test
	public void testRandom() {
		OperationKindsEvaluation evaluation = new OperationKindsEvaluation(
				OperationType.Random);
		evaluation.doEvaluate();

	}
}
