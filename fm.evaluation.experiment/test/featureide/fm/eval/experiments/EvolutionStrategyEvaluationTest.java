package featureide.fm.eval.experiments;

import org.junit.Test;

import featureide.fm.model.EvolutionStrategy;

public class EvolutionStrategyEvaluationTest {

	@Test
	public void testDeleteAllChildren() {
		EvolutionStrategyEvaluation evaluation = new EvolutionStrategyEvaluation(
				EvolutionStrategy.DeleteAllChildren);
		evaluation.doEvaluate();
	}

	@Test
	public void testMergeChildrenToParent() {
		EvolutionStrategyEvaluation evaluation = new EvolutionStrategyEvaluation(
				EvolutionStrategy.MergeChildrenToParent);
		evaluation.doEvaluate();
	}

	@Test
	public void testMergeChildToAnotherCompound() {

		EvolutionStrategyEvaluation evaluation = new EvolutionStrategyEvaluation(
				EvolutionStrategy.MergeChildToAnotherCompound);
		evaluation.doEvaluate();
	}
}
