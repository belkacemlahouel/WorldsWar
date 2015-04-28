package env2.api;

import java.util.List;
import math.MyPoint2D;

public abstract class AbstractCell {

	// public abstract AbstractEnvironment getEnvironment();
	public abstract List<AbstractWorldObject> getObjects();
	public abstract void removeObject(AbstractWorldObject o);
	public abstract void addObject(AbstractWorldObject o);
}

// TODO move in another class file
abstract class AbstractCellPortal {
	
	public abstract boolean isPortal();
	// public abstract int getPortalEnvironmentIdx();
	public abstract AbstractEnvironment getPortalEnvironment();
	public abstract MyPoint2D getPortalArrivalPosition();
}
