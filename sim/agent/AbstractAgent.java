package sim.agent;

import java.util.Iterator;
import java.util.List;

import env2.api.AbstractBody;
import env2.api.AbstractCell;
import env2.api.AbstractWorldObject;
import env2.type.Direction;
import env2.type.Time;

public abstract class AbstractAgent {
	
	protected final int TRIBE_ID;
	
	protected AbstractBody body;

	public AbstractAgent(AbstractBody b, int ID) {
		body = b;
		TRIBE_ID = ID;
	}
	
	/**
	 * Getter for the body
	 * @return body to which this agent is attached
	 */
	public AbstractBody getBody() {
		return body;
	}
	
	/**
	 * Decision process implemented here
	 * The agent applies influences
	 */
	public abstract void live();
	
	/**
	 * Decision process inside
	 * There is this default version, which can be overidden if need be
	 * @return boolean: should the agent kill himself?
	 */
	public boolean killMe() {
		if (body.getLife() < 0 || body.getAge(Time.TIME) > body.getMaxAge())
			return true;
		
		return false;
	}
	
	/**
	 * So agents can know if they belong in the same team or not
	 * @return tribe id: tribe index
	 */
	public int getTribeID() {
		return TRIBE_ID;
	}
	
	
	/**
	 * Behaviour of the ant gatherer when it hasn't find anything.
	 */
	public void wander(){
		//float angle = (float) ((Math.random() - Math.random())/Math.PI * 4);
		Direction direction = this.getBody().getDirection();
		//TODO must be change, in function of the kinematic that will be implemented.
		//influenceKinemtic(direction, angle);
	}
	
	/**
	 * Method to tell if an agent is on the same cell as an object.
	 * @param object, an AbstractWorldObject.
	 * @return true if the agent is on the same position than "object".
	 */
	public boolean isOnSamePosition(AbstractWorldObject object){
		AbstractBody body = this.getBody();
		AbstractCell cell = this.getBody().getEnvironment().getCell(body.getPosition());
		List<AbstractWorldObject> objects = cell.getObjects();
		boolean goodPosition = false;
		Iterator<AbstractWorldObject> iterator = objects.iterator();
		
		while(goodPosition==false && iterator.hasNext()){
			iterator.next();
			goodPosition = iterator.equals(object);
		}
		
		return goodPosition;
	}
}