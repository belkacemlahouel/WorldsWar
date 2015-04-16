package env;

import java.util.ArrayList;
import java.util.List;

import env.api.Movable;
import env.environment.Cave;
import env.environment.Environment;

public class Cell {
	
	// private final static int CAPACITY = 30;
	
	private List<WorldObject> objects;
	private Environment portal;

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
	
	public void newCave(int width, int heigth) {
		portal = new Cave(width, heigth, this);
	}
	
	public void destroyEverything() {
		objects.removeAll(objects);
		portal = null;
	}
	
	public boolean isPortal() {
		return portal != null;
	}
	
	public void usePortal(Movable m) {
		
	}
}
