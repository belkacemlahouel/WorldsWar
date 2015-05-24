package env2.action;

import env2.api.AbstractAction;
import env2.api.AbstractBody;
import env2.api.InterfaceUndertaker;

/**
 * Buries the dead body and creates some MEAT resource
 * @author belka
 *
 */

public class BuryDeadAction extends AbstractAction {

	private InterfaceUndertaker undertaker;
	private AbstractBody deadbody;
	
	public BuryDeadAction(InterfaceUndertaker undertaker, AbstractBody deadbody) {
		this.undertaker = undertaker;
		this.deadbody = deadbody;
	}
	
	@Override
	public void doAction() {
		undertaker.buryDead(deadbody);
	}

}
