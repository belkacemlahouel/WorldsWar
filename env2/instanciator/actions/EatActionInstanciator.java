package env2.instanciator.actions;

import env2.action.EatAction;
import env2.influences.EatInfluence;

public class EatActionInstanciator extends AbstractActionInstanciator {

	@Override
	public EatAction getAction() {
		EatInfluence infl = (EatInfluence) influence;
		return new EatAction(infl.eater, infl.container, infl.resource, infl.qty);
	}
}
