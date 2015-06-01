package env2.api;

import math.MyPoint2D;

public interface InterfaceUndertaker {

	public abstract void buryDead(AbstractBody b);
	
	public abstract MyPoint2D getPosition();
	public abstract AbstractEnvironment getEnvironment();
}
