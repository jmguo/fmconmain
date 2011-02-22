package featureide.fm.operations;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import featureide.fm.eval.EvaluationSpec;
import featureide.fm.generator.ModelGenerator;
import featureide.fm.model.Constraint;
import featureide.fm.model.FeatureModel;
import featureide.fm.model.Constraint.ConstraintType;

public class ConstraintChainValidatorTest {

	FeatureModel fm;

	@Before
	public void setUp() {
		EvaluationSpec spec = new EvaluationSpec();
		spec.setModelNum(9);
		fm = ModelGenerator.getInstance().generateFeatureModel(101, spec);
	}

	@Test
	@Ignore
	public void testInitGraph() {

		ConstraintChainValidator validator = new ConstraintChainValidator(fm);
		validator.initGraphs();

	}

	@Test
	public void testAddConstraint() {
		Iterator<String> features = fm.getFeatureNames().iterator();
		String nameA = features.next();
		String nameB = features.next();
		String nameC = features.next();
		String nameD = features.next();

		Constraint con_1 = new Constraint(nameA, nameB, ConstraintType.require);
		Constraint con_2 = new Constraint(nameA, nameC, ConstraintType.exclude);
		Constraint con_3 = new Constraint(nameB, nameD, ConstraintType.require);
		Constraint con_4 = new Constraint(nameC, nameD, ConstraintType.exclude);

		fm.addConstraint(con_1);
		fm.addConstraint(con_2);

		ConstraintChainValidator validator = new ConstraintChainValidator(fm);
		validator.initGraphs();

		Assert.assertTrue(validator.addConstraint(con_3));
		fm.addConstraint(con_3);

		Assert.assertFalse(validator.addConstraint(con_4));
	}
}
