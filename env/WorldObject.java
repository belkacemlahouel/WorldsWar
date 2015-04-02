package env;

import math.MyPoint2D;

public abstract class WorldObject {

	public abstract String toString();
}

interface Movable {
	
	public abstract void setPosition(MyPoint2D pos);
	public abstract MyPoint2D getPosition();
}

class Wall extends WorldObject {
	
	public Wall() {
		
	}
	
	public String toString() {
		return "WALL";
	}
}

class Pill extends WorldObject {
	
	public Pill() {
		
	}
	
	public String toString() {
		return "PILL";
	}
}
