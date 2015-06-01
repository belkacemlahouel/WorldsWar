package env2.api;

import math.MyPoint2D;

public interface InterfaceSoldier {

	public abstract void hit(AbstractBody o);
	
	public abstract int getStdDmg();
	public abstract int getRndDmg();
	public abstract int getTotDmg();
	
	public abstract MyPoint2D getPosition();
	public abstract AbstractEnvironment getEnvironment();
}
