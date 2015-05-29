package env2.api;

import math.MyPoint2D;

public interface InterfaceNurse {

	public abstract void cure(AbstractBody o);
	
	public abstract int getStdCure();
	public abstract int getRndCure();
	public abstract int getTotCure();
	
	public abstract MyPoint2D getPosition();
	public abstract AbstractEnvironment getEnvironment();
}
