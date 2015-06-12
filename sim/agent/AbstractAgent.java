package sim.agent;

import java.util.Iterator;
import java.util.List;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractCell;
import env2.api.AbstractWorldObject;
import env2.influences.MotionInfluence;
import env2.type.Direction;
import env2.type.Time;

public abstract class AbstractAgent{
	
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
	public abstract MotionInfluence live();
	
	/**
	 * Decision process inside
	 * There is this default version, which can be overidden if need be
	 * @return boolean: should the agent kill himself?
	 */
	public boolean killMe() {
		if (body.getLife() < 0 || body.getAge(Time.getTime()) > body.getMaxAge())
			return true;
		
		return false;
	}
	
	/**
	 * So agents can know if they belong in the same team or not
	 * @return tribe id: tribe index
	 */
	protected int getTribeID() {
		return TRIBE_ID;
	}
	
	public int hashCode() {
		return TRIBE_ID;
	}
	
	
	/**
	 * Behaviour of an agent to move randomly.
	 */
	public MotionInfluence wander() {
		MotionInfluence influence;
		AbstractBody body = this.getBody(); 
		Direction direction = body.getDirection();
		int movingReach = body.getMovingReach();
		
		Direction randomDir = Direction.random();
		
		while (randomDir == direction.opposite()) {
			randomDir = Direction.random();
		}		
		
		MyPoint2D directionPoint = new MyPoint2D(0,0);
		
		// Part to make the direction not straight
		while (movingReach > 0) {
			if (movingReach%2 == 0) {
				directionPoint.add(direction.dx, direction.dy);
			} else {
				directionPoint.add(randomDir.dx, randomDir.dy);
			}
			--movingReach;
		}
		
		// Check for the limit of the environment.
		int x = getBody().getPosition().getX() + randomDir.dx;
		int y = getBody().getPosition().getY() + randomDir.dy;
		
		x = Math.max(0, Math.min(x, getBody().getEnvironment().getWidth()-1));
		y = Math.max(0, Math.min(y, getBody().getEnvironment().getHeight()-1));
		
		directionPoint = new MyPoint2D(x, y);
		
		
		influence = new MotionInfluence(body,directionPoint, body.getEnvironment());
		
		return influence;
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
			goodPosition = goodPosition || iterator.equals(object);
		}
		
		return goodPosition;
	}
}