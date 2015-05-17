package env2.body.antbody;

import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.api.InterfaceGatherer;
import env2.type.Direction;

/**
 * Some methods only might be overriden here
 * @author belka
 *
 */

public final class AntGathererBody extends AntBody implements InterfaceGatherer {

	public AntGathererBody(int TIME, int tribe_id, AbstractEnvironment env, Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
	}

	/*
	 * We assume the quantity provided in this method comes from the InfluenceSolver
	 * Hence, we should never go pass our capacity
	 * 
	 * (non-Javadoc)
	 * @see env2.api.InterfaceGatherer#take(env2.api.AbstractResource, int)
	 */
	public void take(AbstractResource o, int qty) {
		// TODO Auto-generated method stub
	}

	@Override
	public void put(AbstractResource o, int qty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final int getStdTakeQty() {
		return 2;
	}

	@Override
	public final int getRndTakeQty() {
		return 1;
	}

	@Override
	public int getTotTakeQty() {
		return getStdTakeQty() + MyMath.random(-getRndTakeQty(), getRndTakeQty());
	}
}
