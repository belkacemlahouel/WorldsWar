package env2.api;

import math.MyPoint2D;

public interface InterfaceGatherer {
	
	public abstract void take(AbstractResource o, int qty);
	public abstract void put(AbstractResource o, int qty);
	// public abstract void take(AbstractWorldObject o, int qty);
	public abstract int getStdTakeQty();
	public abstract int getRndTakeQty();
	public abstract int getTotTakeQty();
	
	public abstract MyPoint2D getPosition();
	public abstract AbstractEnvironment getEnvironment();
}
