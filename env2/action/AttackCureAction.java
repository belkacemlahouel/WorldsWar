package env2.action;

import env2.api.AbstractAction;
import env2.api.AbstractBody;

/**
 * This class sure breaks the semantics,
 * but this reduces checking...
 * @author belka
 *
 */

public class AttackCureAction extends AbstractAction {
	
	public AbstractBody attacked;
	public int dmg;
	
	public AttackCureAction(AbstractBody attacked, int dmg) {
		this.attacked = attacked;
		this.dmg = dmg;
	}

	public void doAction() {
		attacked.applyLifeVariation(dmg);
	}
}
