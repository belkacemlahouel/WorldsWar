package env2.pheromones;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;
import env2.api.Morphable;
import env2.type.WorldObjectType;

public abstract class AbstractPheromone extends AbstractWorldObject implements Morphable {

	private int count;
	
	private MyPoint2D position;
	private AbstractEnvironment environment;
	
	public AbstractPheromone(int count, MyPoint2D pos, AbstractEnvironment e) {
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
		--count;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public boolean morph() {
		System.err.println("delete");
		// TODO Implement that
		return true;
	}
	
	public abstract String toString();

	public WorldObjectType getType() {
		return WorldObjectType.PHEROMONE;
	}

	public boolean isPerceivable() {
		return true;
	}

	public boolean tic() {
		tac();
		if (getCount() <= 0) {
			return this.morph();
		}
		return false;
	}
}
