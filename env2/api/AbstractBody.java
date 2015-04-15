package env2.api;

import env2.type.Direction;
import env2.type.EffectType;

public abstract class AbstractBody extends AbstractMobileWorldObject {

	public abstract void buildNewFrustrum();
	public abstract AbstractFrustrum getCurrentFrustrum();
	
	/**
	 * TODO To be decided later...
	 * Position? Cell? Environment? TODO
	 */
	
	public abstract AbstractEnvironment getEnvironment();
	public abstract Position getPosition();
	
	public abstract Direction getDirection();
	
	// get current variables
	public abstract int getCurrentLife();
	public abstract int getCapacity();
	public abstract int getReach();
	
	// constants above which we apply random rules
	protected abstract int getStdMaxLife();
	protected abstract int getStdSpeed();
	protected abstract int getStdLifeLoss();
	
	// random part to be implemented, +- X at maximum
	protected abstract int getRndMaxLife();
	protected abstract int getRndSpeed();
	protected abstract int getRndLifeLoss();
	
	// total quantities, API for the user
	public int getTotMaxLife() 		{ return getStdMaxLife() + getRndMaxLife(); }
	public int getTotSpeed() 		{ return getStdSpeed() + getRndSpeed(); }
	public int getTotLifeLoss() 	{ return getStdLifeLoss() + getRndLifeLoss(); }
	
	// action methods
	public abstract void eat(AbstractWorldObject o, int qty);
	protected abstract EffectType getEffect(AbstractWorldObject o);
	public abstract int getAge();
	public abstract boolean isFriend(AbstractBody b);
	
	// Should we put the colony here or in the Agent side? TODO Ask prof!
}
