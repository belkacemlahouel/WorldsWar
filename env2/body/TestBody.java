package env2.body;

import java.util.ArrayList;
import java.util.List;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.type.Direction;
import env2.type.EffectType;
import env2.type.WorldObjectType;

public final class TestBody extends ClassicBody 
{	
	private List<AbstractResource> transported;
	private int occupiedCapacity;

	public TestBody(AbstractEnvironment e, Direction dir, MyPoint2D pos, float TIME) {
		super(e, dir, pos, TIME);
		transported = new ArrayList<AbstractResource>();
		occupiedCapacity = 0;
	}

	public int getCapacity() {
		return 10;
	}
	public int getEatingSpeed() {
		return 5;
	}

	public int getReach() {
		return 2;
	}

	protected int getStdMaxLife() {
		return 100;
	}

	protected int getStdSpeed() {
		return 1;
	}
	@Override
	protected int getStdStrength() {
		return 2;
	}

	// TODO We may make the capacities change in function of the Body age for more realism
	// TODO Everywhere! this is just an example
	protected int getStdLifeLoss(float CURRENT_TIME) {
		return (int) (3 + getAge(CURRENT_TIME)/getMaxAge());
	}

	protected int getRndMaxLife() {
		return 1;
	}

	protected int getRndSpeed() {
		return 0;
	}

	protected int getRndLifeLoss() {
		return 2;
	}
	@Override
	protected int getRndStrength() {
		return 1;
	}

	public void eat(AbstractResource o, int qty) {
		int tmp_qty = qty;
		if (tmp_qty + occupiedCapacity > getEatingSpeed())
			tmp_qty = getCapacity() - occupiedCapacity;
		o.pick(tmp_qty);
		
		switch(getEffect(o)) {
			case GOOD:
				mylife += tmp_qty*10;
				return;
			case BAD:
				mylife -= tmp_qty*10;
				return;
			default:
				mylife += tmp_qty*2;
				return;
		}
	}

	// TODO instead of a switch, we may use a data-structure, final, like an hashset
	// TODO We may use a common data-structure for all members of the same tribe
	protected EffectType getEffect(AbstractWorldObject o) {
		switch(o.getType()) {
			case SUGAR :
				return EffectType.GOOD;
				
			case MEAT :
				return EffectType.GOOD;
				
			default:
				return EffectType.NEUTRAL;
		}
	}

	public int getTribeID() {
		return TRIBE_ID;
	}

	public WorldObjectType getType() {
		return WorldObjectType.ANTBODY; // TODO This should not be here
	}

	public int getOccupiedCapacity() {
		return occupiedCapacity;
	}

	@Override
	public int getMaxAge() {
		return 300;
	}
}
