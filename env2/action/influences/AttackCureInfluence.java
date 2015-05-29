package env2.action.influences;

import env2.api.AbstractBody;

public class AttackCureInfluence {

	public AbstractBody attacked;
	public int dmg;
	
	public AttackCureInfluence(AbstractBody attacked, int dmg) {
		this.attacked = attacked;
		this.dmg = dmg;
	}
}
