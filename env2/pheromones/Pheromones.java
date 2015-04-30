package env2.pheromones;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;
import env2.api.Morphable;
import env2.type.WorldObjectType;

public class Pheromones extends AbstractWorldObject implements Morphable{

	int count;
	
	MyPoint2D position;
	AbstractEnvironment environment;
	
	public Pheromones(int count, MyPoint2D pos, AbstractEnvironment e){
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

	@Override
	public void tac() {
		count --;
	}
	@Override
	public int getCount() {
		return this.count;
	}
	@Override
	public boolean morph() {
		System.out.println("test");
		return false;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public WorldObjectType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPerceivable() {
		// TODO Auto-generated method stub
		return false;
	}

	


	
}
