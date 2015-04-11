package env;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	
	private List<WorldObject> objects;

	public Cell() {
		objects = new ArrayList<WorldObject>();
	}
	
	public boolean isEmpty() {
		return objects.isEmpty();
	}
	
	public List<WorldObject> getObjects() {
		return objects;
	}
	
	public void addObject(WorldObject o) {
		objects.add(o);
	}
	
	public void removeObject(WorldObject o) {
		objects.remove(o);
	}
}
