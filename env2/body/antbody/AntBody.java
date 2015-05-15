package env2.body.antbody;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.body.ClassicBody;
import env2.type.Direction;
import env2.type.EffectType;
import env2.type.WorldObjectType;

public class AntBody extends ClassicBody {

	public AntBody(AbstractEnvironment e, Direction dir, MyPoint2D pos,
			float TIME) {
		super(e, dir, pos, TIME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEatingSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getReach() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getStdLifeLoss(float CURRENT_TIME) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getStdMaxLife() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getStdSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getStdStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getRndLifeLoss() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getRndMaxLife() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getRndSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getRndStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void eat(AbstractResource o, int qty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected EffectType getEffect(AbstractWorldObject o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOccupiedCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTribeID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WorldObjectType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
