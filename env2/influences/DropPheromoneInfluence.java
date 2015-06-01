package env2.influences;

import java.util.NoSuchElementException;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractInfluence;
import env2.type.InfluenceType;
import env2.type.WorldObjectType;

public class DropPheromoneInfluence extends AbstractInfluence {

	public final int CREATION_TIME;
	public final int TRIBE_ID;
	public final WorldObjectType TYPE;
	public final AbstractEnvironment ENV;
	public final MyPoint2D POS;
	public final float POW;
	
	public DropPheromoneInfluence(WorldObjectType type, AbstractEnvironment e, MyPoint2D position, int tribe_id, float power, int time) {
		if (WorldObjectType.isPheromone(type)) {
			CREATION_TIME = time;
			TRIBE_ID = tribe_id;
			TYPE = type;
			ENV = e;
			POS = position;
			POW = power;
		} else throw new NoSuchElementException("This is not a Pheromone!");
	}

	@Override
	public InfluenceType getType() {
		return InfluenceType.DROP_PHEROMONE;
	}
}
