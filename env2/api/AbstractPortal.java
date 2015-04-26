package env2.api;

import math.MyPoint2D;

public interface AbstractPortal {
	
	public abstract boolean isPortal();
	public abstract AbstractEnvironment getEnvironment();
	public abstract MyPoint2D getArrivalPosition();
}
