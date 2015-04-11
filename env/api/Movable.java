package env.api;

import math.MyPoint2D;

public interface Movable {
	
	public abstract void setPosition(MyPoint2D pos);
	public abstract MyPoint2D getPosition();
}
