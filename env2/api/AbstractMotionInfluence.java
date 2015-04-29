package env2.api;

import math.MyPoint2D;

public abstract class AbstractMotionInfluence extends AbstractInfluence {
	
	public abstract AbstractEnvironment getStartEnvironment();
	public abstract MyPoint2D getStartPosition();
	public abstract AbstractEnvironment getArrivalEnvironment();
	public abstract MyPoint2D getArrivalPosition();
}
