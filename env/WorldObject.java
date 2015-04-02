package env;

import math.MyPoint2D;

public abstract class WorldObject {

	public abstract String toString();
	
	public boolean isWall() {
		return toString().contains("WALL");
	}
	
	public boolean isPill() {
		return toString().contains("PILL");
	}
	
	public boolean isGhost() {
		return toString().contains("GHOST");
	}
	
	public boolean isPacman() {
		return toString().contains("PACMAN");
	}
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
