package env2.instanciator.actions;

import env2.action.DropPheromoneAction;
import env2.influences.DropPheromoneInfluence;

public class DropPheromoneActionInstanciator extends AbstractActionInstanciator {

	@Override
	public DropPheromoneAction getAction() {
		DropPheromoneInfluence infl = (DropPheromoneInfluence) influence;
		return new DropPheromoneAction(infl.TYPE, infl.ENV, infl.POS, infl.TRIBE_ID, infl.POW, infl.CREATION_TIME);
	}
}
