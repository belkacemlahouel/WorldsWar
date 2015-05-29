package env2.action.influences;

import env2.api.AbstractBody;
import env2.api.AbstractInfluence;
import env2.type.InfluenceType;

public class AttackCureInfluence extends AbstractInfluence {

	public AbstractBody attacked;
	public int dmg;
	
	public AttackCureInfluence(AbstractBody attacked, int dmg) {
		this.attacked = attacked;
		this.dmg = dmg;
	}

	@Override
	public InfluenceType getType() {
		return InfluenceType.ATTACK_CURE;
	}
}
