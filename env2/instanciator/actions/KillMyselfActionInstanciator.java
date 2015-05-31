package env2.instanciator.actions;

import env2.action.KillMyselfAction;
import env2.influences.KillMyselfInfluence;

public class KillMyselfActionInstanciator extends AbstractActionInstanciator {

	@Override
	public KillMyselfAction getAction() {
		KillMyselfInfluence infl = (KillMyselfInfluence) influence;
		return new KillMyselfAction(infl.attacked);
	}
}
