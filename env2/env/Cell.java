package env2.env;

import java.util.ArrayList;
import java.util.List;

import env2.api.AbstractCell;
import env2.api.AbstractWorldObject;

public class Cell extends AbstractCell {

	private List<AbstractWorldObject> objects;
	
	public Cell() {
		objects = new ArrayList<AbstractWorldObject>();
	}
	
	public List<AbstractWorldObject> getObjects() {
		return objects;
	}

	public void removeObject(AbstractWorldObject o) {
		objects.remove(o);
	}

	public void addObject(AbstractWorldObject o) {
		objects.add(o);
	}
}
