package env2.api;

import math.MyPoint2D;

public abstract class AbstractEnvironment {

	public abstract AbstractCell getCell(int x, int y);
	public abstract int getWidth();
	public abstract int getHeight();
	
	public AbstractCell getCell(MyPoint2D position) {
		return getCell(position.getX(), position.getY());
	}
}
