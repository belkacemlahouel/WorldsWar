package env2.api;

import math.MyPoint2D;

public interface InterfaceMother {

	public abstract void addBaby();
	// How to connect an agent to the newly created body?
	// AND TODO we have to find a way to add this agent to the simulation loop.
	
	public abstract int getTribeID();
	public abstract MyPoint2D getPosition();
	public abstract AbstractEnvironment getEnvironment();
}
