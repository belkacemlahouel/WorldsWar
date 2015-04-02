package env;

public class Cell {
	
	private WorldObject object;

	public Cell() {
		object = null;
	}
	
	public boolean isEmpty() {
		return object == null;
	}
	
	public WorldObject getObject() {
		return object;
	}
	
	public void addObject(WorldObject o) {
		object = o;
	}
}
