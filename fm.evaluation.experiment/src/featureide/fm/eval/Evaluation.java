package featureide.fm.eval;

public interface Evaluation {
	public static enum EvaluationType {
		FeatureNumber, EditKinds, EditNumber, ES
	}

	public static String rootPath = "D:\\Workspaces\\ConsistentFMs\\";

	public String getName();

	public EvaluationSpec[] createSpecs();

	public void doEvaluate();
}
