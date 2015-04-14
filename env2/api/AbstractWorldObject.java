package env2.api;

import env2.type.WorldObjectType;

public abstract class AbstractWorldObject {

	public abstract AbstractEnvironment getEnvironment();
	public abstract WorldObjectType getType();
	public abstract boolean isPerceivable();
}
