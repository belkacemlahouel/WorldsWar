package env2.frustrum;

import math.MyPoint2D;
import env2.api.AbstractWorldObject;

public class Perception {
	
	public AbstractWorldObject object;
	public MyPoint2D position;
	
	public Perception(AbstractWorldObject object, MyPoint2D position) {
		this.object = object;
		this.position = position;
	}
	
	public String toString() {
		return "" + object + " at " + position;
	}
}
