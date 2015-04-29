package env2.api;

import java.util.List;

public abstract class AbstractCell {

	// public abstract AbstractEnvironment getEnvironment();
	public abstract List<AbstractWorldObject> getObjects();
	public abstract void removeObject(AbstractWorldObject o);
	public abstract void addObject(AbstractWorldObject o);
	public abstract boolean isPortal();
}
