package env2.influences;

import env2.api.AbstractBody;

public class KillMyselfInfluence extends AttackCureInfluence {

	public KillMyselfInfluence(AbstractBody attacked) {
		super(attacked, Integer.MIN_VALUE);
	}
}
