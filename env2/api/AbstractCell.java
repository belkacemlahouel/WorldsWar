package env2.api;

import java.util.List;

public abstract class AbstractCell {

	// public abstract AbstractEnvironment getEnvironment();
	public abstract List<AbstractWorldObject> getObjects();
	public abstract boolean removeObject(AbstractWorldObject o);
	public abstract void addObject(AbstractWorldObject o);
	public abstract boolean isPortal();
	
	public List<? extends AbstractAction> solveInfluences() {
		// TODO Auto-generated method stub
		return null;
	}
}
