package env2.instanciator.actions;

import env2.action.BuryDeadAction;
import env2.influences.BuryDeadInfluence;

public class BuryDeadActionInstanciator extends AbstractActionInstanciator {

	@Override
	public BuryDeadAction getAction() {
		BuryDeadInfluence infl = (BuryDeadInfluence) influence;
		return new BuryDeadAction(infl.undertaker, infl.deadbody);
	}
}
