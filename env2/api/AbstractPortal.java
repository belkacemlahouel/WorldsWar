package env2.api;

import math.MyPoint2D;

public abstract class AbstractPortal extends AbstractCell {
	
	public abstract boolean isPortal();
	public abstract AbstractEnvironment getEnvironment();
	public abstract MyPoint2D getArrivalPosition();
}
