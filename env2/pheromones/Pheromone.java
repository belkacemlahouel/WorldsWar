package env2.pheromones;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;
import env2.api.Morphable;
import env2.env.Environment;
import env2.type.WorldObjectType;

public class Pheromone extends AbstractWorldObject implements Morphable {

	int count;
	
	MyPoint2D position;
	AbstractEnvironment environment;
	Environment globalEnv;
	
	public Pheromone(int count, MyPoint2D pos, AbstractEnvironment e) {
		this.count = count;
		position = pos;
		this.environment = e;
	}
	
	public AbstractEnvironment getEnvironment() {
		return environment;
	}
	
	public MyPoint2D getPosition() {
		return position;
	}

	public void tac() {
		count --;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public boolean morph() {
		System.out.println("delete");
		return true;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public WorldObjectType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPerceivable() {
		// TODO Auto-generated method stub
		return false;
	}
}
