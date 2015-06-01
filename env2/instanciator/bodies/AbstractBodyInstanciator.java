package env2.instanciator.bodies;

import java.util.HashMap;
import java.util.List;

import sim.agent.AbstractAgent;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceMother;
import env2.influences.CreateBabyInfluence;
import env2.instanciator.AbstractInstanciator;
import env2.type.Direction;

public abstract class AbstractBodyInstanciator extends AbstractInstanciator {
	
	public static int TRIBE_ID;
	public static AbstractEnvironment ENV;
	public static MyPoint2D POS;
	public static Direction DIR = Direction.EAST;
	
	public abstract AbstractBody getNew();
	public abstract AbstractAgent getAgent();
	
	/**
	 * Mothers list and baby influences from simulator for this instanciator.
	 * This is linked to the fact that Simulator implements Singleton design pattern.
	 * Hence, we have only one instance of this list set during the whole execution.
	 */
	
	private static HashMap<InterfaceMother, List<CreateBabyInfluence>> MOTHERS;
	private static int NB_INIT_MOTHERS = 0;
	
	public static void setMothers(HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers) {
		if (NB_INIT_MOTHERS == 0) {
			MOTHERS = mothers;
			++NB_INIT_MOTHERS;
		}
	}
	
	public static HashMap<InterfaceMother, List<CreateBabyInfluence>> getMothers() {
		return MOTHERS;
	}
}
