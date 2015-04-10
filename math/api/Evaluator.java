package math.api;

public interface Evaluator {

	/**
	 * @return evaluation of this position by the calling agent
	 */
	public int interest(Position pos);
}
