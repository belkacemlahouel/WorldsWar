package env;

import math.MyPoint2D;

public abstract class WorldObject {

	public abstract String toString();
}

interface Movable {
	
	public abstract void setPosition(MyPoint2D pos);
	public abstract MyPoint2D getPosition();
}
