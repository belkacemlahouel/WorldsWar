package env2.api;

public abstract class AbstractEnvironment {

	public abstract AbstractCell getCell(int x, int y);
	public abstract int getWidth();
	public abstract int getHeight();
}
