package env2.api;

import math.MyPoint2D;

public interface InterfacePortal {
	
	public abstract boolean isPortal();
	public abstract AbstractEnvironment getArrivalEnvironment();
	public abstract MyPoint2D getArrivalPosition();
}
