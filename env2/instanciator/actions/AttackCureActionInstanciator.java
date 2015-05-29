package env2.instanciator.actions;

import env2.action.AttackCureAction;
import env2.api.AbstractAction;

public class AttackCureActionInstanciator extends AbstractActionInstanciator {
	
	@Override
	public AbstractAction getAction() {
		return new AttackCureAction(TARGET, LIFE_VAR);
	}
}
