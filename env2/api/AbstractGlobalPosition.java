package env2.api;

public abstract class AbstractGlobalPosition {
	
	public abstract int getX();
	public abstract int getY();
	public abstract int getEnvironmentIdx();
	public abstract void setX(int newX);
	public abstract void setY(int newY);
	public abstract void setEnvironmentIdx(int newEnvironmentIdx);
}
