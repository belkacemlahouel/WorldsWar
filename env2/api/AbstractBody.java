package env2.api;

import math.MyPoint2D;
import env2.type.Direction;
import env2.type.EffectType;
import env2.type.FrustrumType;
import env2.type.Time;

public abstract class AbstractBody extends AbstractMobileWorldObject {

	public abstract void buildNewFrustrum(FrustrumType type);
	public abstract AbstractFrustrum getCurrentFrustrum();
	
	/**
	 * TODO To be decided later...
	 * Position? Cell? Environment? TODO
	 */
	
	public abstract Direction getDirection();
	
	// get current variables
	public abstract int getCurrentLife();
	public abstract int getCapacity();
	public abstract int getReach();
	
	// constants above which we apply random rules
	protected abstract int getStdMaxLife();
	protected abstract int getStdSpeed();
	protected abstract int getStdLifeLoss(float TIME);
	
	// random part to be implemented, +- X at maximum
	protected abstract int getRndMaxLife();
	protected abstract int getRndSpeed();
	protected abstract int getRndLifeLoss();
	
	// total quantities, API for the user
	public int getTotMaxLife() 		{ return getStdMaxLife() + getRndMaxLife(); }
	public int getTotSpeed() 		{ return getStdSpeed() + getRndSpeed(); }
	public int getTotLifeLoss() 	{ return getStdLifeLoss(Time.TIME) + getRndLifeLoss(); } // FIXME TIME Cst access?
	
	// action methods
	public abstract void eat(AbstractResource o, int qty);
	protected abstract EffectType getEffect(AbstractWorldObject o);
	public abstract int getAge(float TIME);
	public abstract int getMaxAge();
	public abstract boolean isFriend(AbstractBody b);
	
	public abstract int getOccupiedCapacity();
	
	// Should we put the colony here or in the Agent side? TODO Ask prof!
	
	// Annother idea is to put only a tribe index so that everyone cannot access his friends,
	// But if the Body perceives them, it can say when judging from its appearence if they are friends/not.
	// I think this would be best to put this in the Agent part, but for now I do it this way.
	public abstract int getTribeID();
}
