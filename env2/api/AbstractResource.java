package env2.api;

public abstract class AbstractResource extends AbstractWorldObject {
	public abstract int getQuantity();
	public abstract AbstractResource pick(int quantity);
	public abstract void add(int quantity);
}
