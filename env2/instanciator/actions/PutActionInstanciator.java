package env2.instanciator.actions;

import env2.action.PutAction;
import env2.influences.PutInfluence;

public class PutActionInstanciator extends AbstractActionInstanciator {

	@Override
	public PutAction getAction() {
		PutInfluence infl = (PutInfluence) influence;
		return new PutAction(infl.actor, infl.res, infl.qty);
	}
}
