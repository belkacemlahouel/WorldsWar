package env2.api;

import math.MyPoint2D;
import env2.type.WorldObjectType;

public abstract class AbstractWorldObject {

	public abstract WorldObjectType getType();
	public abstract boolean isPerceivable();
	

	public abstract AbstractEnvironment getEnvironment();
	public abstract MyPoint2D getPosition();
	
}
