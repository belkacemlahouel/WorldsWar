package env2.body.antbody;

import java.util.Collection;

import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
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
	 * Hence, we should never go pass our capacity...
	 * 
	 * (non-Javadoc)
	 * @see env2.api.InterfaceGatherer#take(env2.api.AbstractResource, int)
	 */
	private AbstractResource getResourceOfSameType(AbstractResource o, Collection<? extends AbstractWorldObject> container) {
		for (AbstractWorldObject res : container) {
			if (res.getType() == o.getType()) {
				return ((AbstractResource) res);
			}
		}
		
		return null;
	}
	
	public void take(AbstractResource o, int qty) {
		AbstractResource res = getResourceOfSameType(o, getCargo());
		if (res == null)
			getCargo().add(o);
		
		res.add(qty);
	}

	@Override
	public void put(AbstractResource o, int qty) {
		AbstractResource res = getResourceOfSameType(o, getEnvironment().getCell(getPosition()).getObjects());
		if (res == null)
			getEnvironment().getCell(getPosition()).getObjects().add(o);
		
		res.add(qty);
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
