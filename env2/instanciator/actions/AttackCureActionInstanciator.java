package env2.instanciator.actions;

import env2.action.AttackCureAction;
import env2.api.AbstractAction;
import env2.influences.AttackCureInfluence;

public class AttackCureActionInstanciator extends AbstractActionInstanciator {
	
	@Override
	public AbstractAction getAction() {
		AttackCureInfluence infl = (AttackCureInfluence) influence;
		return new AttackCureAction(infl.attacked, infl.dmg);
	}
}
