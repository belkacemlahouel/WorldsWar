package env2.pheromones;

import env2.action.RemoveMeAction;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;
import env2.type.WorldObjectType;
import math.MyPoint2D;

/**
 * Representation of an object pheromone.
 * 
 * In the environment the pheromone is detect in that surface :
 * X X X
 * X O X
 * X X X
 * Where O is the position of the pheromone.
 */

public final class Pheromone extends AbstractWorldObject {
	
	public static final int MAX_POWER = 3;
	public static final float SPREADING_RATIO = 3/4;
	public static final float POWER_LOSS = 0.1f;
	
	private final WorldObjectType type;
	private final AbstractEnvironment env;
	private final int tribeId; // tribeId is the id of the tribe which can detect the pheromone.
	private MyPoint2D pos;
	private float power;
	
	private final int CREATION_TIME;
	
	public Pheromone(WorldObjectType pType, AbstractEnvironment environment, int id, MyPoint2D position, float pow, int creation_time) {
		type = pType;
		env = environment;
		tribeId = id;
		pos = position;
		power = pow;
		
		CREATION_TIME = creation_time;
	}
	
	/**
	 * Returns the current power
	 * @param d
	 * @return the current power
	 */
	public float getPower() {
		return power;
	}
	
	/**
	 * Decrease the power so that a pheromone can stay in environment.
	 * @param step, the time step from the last update (delta time).
	 */
	public void updatePower(int DELTA_TIME) {
		power -= DELTA_TIME*POWER_LOSS;
		
		if(power <= 0) {
			(new RemoveMeAction(this, env.getCell(pos).getObjects())).doAction(); // TODO Check if any exception...
		}
	}
	
	public void updatePowerNow(int CURRENT_TIME) {
		power = (CURRENT_TIME-CREATION_TIME)*POWER_LOSS;
		
		if(power <= 0) {
			(new RemoveMeAction(this, env.getCell(pos).getObjects())).doAction(); // TODO Check if any exception...
		}
	}

	/**
	 * Return the type of the pheromone.
	 */
	public WorldObjectType getType() {
		return type;
	}

	@Override
	public boolean isPerceivable() {
		return true;
	}
	
	public int getTribeID() {
		return tribeId;
	}
	
	/**
	 * Check if a body can detect a pheromone.
	 * @param body, the agent's body that can potentially feel the pheromone.
	 * @return true, if the body can detect the pheromone.
	 */
	/*public boolean isDetected(AbstractBody body) {
		MyPoint2D pos = body.getPosition();
		int id = body.getTribeID();
		
		//check if the body is part of the tribe that put the pheromone at this place.
		if(id != tribeId)
		{
			return false;
		}
		else
		{
			//check if the body is in the area to feel the pheromone.
			return this.surface.containsKey(pos);
		}
	}*/
}