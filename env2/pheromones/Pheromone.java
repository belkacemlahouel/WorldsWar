package env2.pheromones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;
import env2.type.Time;
import env2.type.WorldObjectType;
import math.MyPoint2D;

 
/**
 * Representation of an object pheromon.
 * 
 * In the environment the pheromon is detect in that surface :
 * X X X
 * X O X
 * X X X
 * Where O is the position of the pheromon.
 */
public class Pheromone extends AbstractWorldObject{
	private WorldObjectType type;
	private int remainingTime;
	private AbstractEnvironment env;
	//tribeId is the id of the tribe which can detect the pheromon.
	private int tribeId;
	private MyPoint2D pos;
	private float power;
	
	Pheromone(WorldObjectType pType, AbstractEnvironment environment, int id, MyPoint2D position, float pow)
	{
		type = pType;
		remainingTime = getMaxLapse();
		env = environment;
		tribeId = id;
		pos = position;
		power = pow;
		
		if(power == 2.0)
		{
			Time time = new Time();
			buildSurface(pos, time);
		}
	}
	
	/**
	 * Get the max time that a pheromon can stay in environment.
	 * @return the max time.
	 */
	public int getMaxLapse()
	{
		return 30;
	}
	
	/**
	 * Decrease the time a pheromon could still stay in environment.
	 * @param step, the step to decrease remaining time.
	 */
	public void decreaseTime(int step)
	{
		remainingTime = remainingTime - step;
		
		if(remainingTime <= 0)
		{
			this.delete();
		}
	}

	/**
	 * Delete a pheromon.
	 */
	private void delete()
	{
		Pheromone object = this;
		object = null;
		
	}
	
	/**
	 * Create the surface detected by an agent
	 * @param position, the position of the pheromon.
	 */
	private void buildSurface(MyPoint2D position, Time time)
	{
		
				MyPoint2D newPos = position;
				newPos.add(-1,1);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
				
				newPos = position;
				newPos.add(0,1);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
				
				newPos = position;
				newPos.add(1,1);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
				
				newPos = position;
				newPos.add(-1,0);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
				
				newPos = position;
				newPos.add(1,0);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
				
				newPos = position;
				newPos.add(-1,-1);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
				
				newPos = position;
				newPos.add(0,-1);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
				
				newPos = position;
				newPos.add(1,-1);
				if(testExistence(newPos))
				{
					new Pheromone(type, env, tribeId, newPos, powerRegardToTimeAfterCreation(1.0, time));
				}
	}
	
	/**
	 * Dicrease the power of a pheromon in function of the time passed since the demand of creation.
	 * @param d
	 * @return the power dicreased.
	 */
	private float powerRegardToTimeAfterCreation(double d, Time time)
	{
		return (float) d;
	}

	/**
	 * Test if a position exist in the environment.
	 * @param pos the position to test.
	 * @return the result of the test.
	 */
	private boolean testExistence(MyPoint2D pos)
	{
		int width = env.getWidth();
		int height = env.getHeight();
		
		if(pos.getX()<=width && pos.getY()<=height)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Return type of the pheromon.
	 */
	public WorldObjectType getType() {
		return type;
	}

	@Override
	public boolean isPerceivable() {
		return true;
	}
	
	/**
	 * Check if a body can detect a pheromon.
	 * @param body, the agent's body that can potentialy feel the pheromon.
	 * @return true, if the body can detect the pheromon.
	 */
	/*public boolean isDetected(AbstractBody body)
	{
		MyPoint2D pos = body.getPosition();
		int id = body.getTribeID();
		
		//check if the body is part of the tribe that put the pheromon at this place.
		if(id != tribeId)
		{
			return false;
		}
		else
		{
			//check if the body is in the area to feel the pheromon.
			return this.surface.containsKey(pos);
		}
	}*/
}