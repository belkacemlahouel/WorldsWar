package env2.action;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractAction;
import env2.api.AbstractEnvironment;
import env2.pheromones.Pheromone;
import env2.type.WorldObjectType;

public class DropPheromoneAction extends AbstractAction {

	private final int CREATION_TIME;
	private final int TRIBE_ID;
	private final WorldObjectType TYPE;
	private final AbstractEnvironment ENV;
	private final MyPoint2D POS;
	private final float POW;
	
	public DropPheromoneAction(WorldObjectType type, AbstractEnvironment e, MyPoint2D position, int tribe_id, float power, int time) {
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
	public void doAction() {
		/* this cell */
		ENV.getCell(POS).addObject(new Pheromone(TYPE, ENV, TRIBE_ID, POS, POW, CREATION_TIME));
		dispatch();
	}
	
	private void dispatch() {
		LinkedList<MyPoint2D> all_points = new LinkedList<>();
		
		all_points.add(POS.addNew(-1, 1));
		all_points.add(POS.addNew(0, 1));
		all_points.add(POS.addNew(1, 1));
		all_points.add(POS.addNew(-1, 0));
		all_points.add(POS.addNew(1, 0));
		all_points.add(POS.addNew(-1, -1));
		all_points.add(POS.addNew(0, -1));
		all_points.add(POS.addNew(1, -1));
		
		for (MyPoint2D tmp : all_points) {
			if (MyMath.isIn(tmp.getX(), tmp.getY(), ENV.getWidth(), ENV.getHeight()))
				ENV.getCell(tmp).addObject(new Pheromone(TYPE, ENV, TRIBE_ID, tmp, POW*Pheromone.SPREADING_RATIO, CREATION_TIME));
		}
	}
}
