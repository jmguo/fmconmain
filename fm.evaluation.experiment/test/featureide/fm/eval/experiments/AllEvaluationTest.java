package featureide.fm.eval.experiments;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { FeatureNumberEvaluationTest.class,
		EditKindsEvaluationTest.class, EditNumberEvaluationTest.class,
		EvolutionStrategyEvaluationTest.class })
public class AllEvaluationTest {

}
