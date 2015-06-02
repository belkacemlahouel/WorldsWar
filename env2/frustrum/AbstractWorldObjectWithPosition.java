package env2.frustrum;

import math.MyPoint2D;
import env2.api.AbstractWorldObject;

public class AbstractWorldObjectWithPosition {
	
	public AbstractWorldObject object;
	public MyPoint2D position;
	
	public AbstractWorldObjectWithPosition(AbstractWorldObject object, MyPoint2D position) {
		this.object = object;
		this.position = position;
	}
}
