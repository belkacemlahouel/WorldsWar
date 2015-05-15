package env2.api;

import math.MyPoint2D;

public interface Morphable {

	
	// public default boolean tic() {
	// 	tac();
	// 	if (getCount() <= 0) {
	// 		return this.morph();
	// 	}
	// 	return false;
	// }
	
	public abstract boolean tic();
	
	public abstract int getCount(); 
	public abstract void tac();
	
	/*
	 * morph return true if the element has to be delete after the tic function
	 */
	public abstract boolean morph();
	
	public abstract AbstractEnvironment getEnvironment();
	public abstract MyPoint2D getPosition();
}
