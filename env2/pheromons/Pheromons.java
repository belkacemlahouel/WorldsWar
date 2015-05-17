package env2.pheromons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import env2.api.AbstractEnvironment;
import math.MyPoint2D;


//TODO add another level where the pheromon is less "fellable". 
/**
 * Representation of an object pheromon.
 * 
 * In the environment the pheromon is detect in that surface :
 * X X X
 * X O X
 * X X X
 * Where O is the position of the pheromon.
 */
class Pheromons {
	private PheromonsType type;
	private int remainingTime;
	private AbstractEnvironment env;
	//tribeId is the id of the tribe which can detect the pheromon.
	private int tribeId;
	private MyPoint2D pos;
	//surface contains the surface where the pheromon can be detect by an agent and the "fellable" characteristic.
	private HashMap<MyPoint2D, Integer> surface = new HashMap<MyPoint2D, Integer>(8);
	
	Pheromons(PheromonsType pType, AbstractEnvironment environment, int id, MyPoint2D position)
	{
		type = pType;
		remainingTime = getMaxLapse();
		env = environment;
		tribeId = id;
		pos = position;
		
		
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
	 * Delete a pheromons.
	 */
	private void delete()
	{
		Pheromons object = this;
		object = null;
		
	}
	
	/**
	 * Create the surface detected by an agent
	 * @param position, the position of the pheromon.
	 */
	private void buildSurface(MyPoint2D position)
	{
		
				MyPoint2D newPos = position;
				newPos.add(-1,1);
				surface.put(newPos,1);
				
				newPos = position;
				newPos.add(0,1);
				surface.put(newPos,1);
				
				newPos = position;
				newPos.add(1,1);
				surface.put(newPos,1);
				
				newPos = position;
				newPos.add(-1,0);
				surface.put(newPos,1);
				
				surface.put(position,2);
				
				newPos = position;
				newPos.add(1,0);
				surface.put(newPos,1);
				
				newPos = position;
				newPos.add(-1,-1);
				surface.put(newPos,1);
				
				newPos = position;
				newPos.add(0,-1);
				surface.put(newPos,1);
				
				newPos = position;
				newPos.add(1,-1);
				surface.put(newPos,1);
	}
}