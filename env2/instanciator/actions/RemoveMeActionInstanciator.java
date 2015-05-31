package env2.instanciator.actions;

import env2.action.RemoveMeAction;
import env2.influences.RemoveMeInfluence;

public class RemoveMeActionInstanciator extends AbstractActionInstanciator {

	@Override
	public RemoveMeAction getAction() {
		RemoveMeInfluence infl = (RemoveMeInfluence) influence;
		return new RemoveMeAction(infl.TO_REMOVE, infl.CONTAINER);
	}
}
