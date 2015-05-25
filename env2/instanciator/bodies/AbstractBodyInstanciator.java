package env2.instanciator.bodies;

import sim.agent.AbstractAgent;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.instanciator.AbstractInstanciator;
import env2.type.Direction;

public abstract class AbstractBodyInstanciator extends AbstractInstanciator {
	
	public static int TRIBE_ID;
	public static AbstractEnvironment ENV;
	public static MyPoint2D POS;
	public static Direction DIR = Direction.EAST;
	
	public abstract AbstractBody getNew();
	public abstract AbstractAgent getAgent();
}
