package env2.api;

import math.MyPoint2D;

public abstract class AbstractMobileWorldObject extends AbstractWorldObject {

	public abstract void move(AbstractEnvironment e, MyPoint2D newpos);
}
