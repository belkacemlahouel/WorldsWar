package env2.action;

import env2.api.AbstractBody;

public class KillMyselfAction extends AttackCureAction {
	
	public KillMyselfAction(AbstractBody attacked) {
		super(attacked, Integer.MIN_VALUE);
	}
}
