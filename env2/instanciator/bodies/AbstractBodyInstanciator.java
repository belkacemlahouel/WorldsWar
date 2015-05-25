package env2.instanciator.bodies;

import sim.agent.AbstractAgent;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.instanciator.AbstractInstanciator;
import env2.type.Direction;

public abstract class AbstractBodyInstanciator extends AbstractInstanciator {
	
	public int TRIBE_ID;
	public AbstractEnvironment ENV;
	public MyPoint2D POS;
	public Direction DIR;
	
	public abstract AbstractBody getNew();
	public abstract AbstractAgent getAgent();
}
