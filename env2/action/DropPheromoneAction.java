package env2.action;

import env2.api.AbstractAction;
import env2.api.AbstractCell;
import env2.pheromones.Pheromone;

public class DropPheromoneAction extends AbstractAction {

	private Pheromone pheromone;
	private AbstractCell cell;
	
	public DropPheromoneAction(Pheromone p, AbstractCell c) {
		pheromone = p;
		cell = c;
	}
	
	@Override
	public void doAction() {
		cell.addObject(pheromone);
	}
}
