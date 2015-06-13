package env2.action;

import java.util.Collection;

import env2.api.AbstractAction;
import env2.api.AbstractBody;
import env2.api.AbstractCell;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceUndertaker;
import env2.influences.RemoveMeInfluence;

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
		AbstractCell cell = deadbody.getCell();
		RemoveMeInfluence remove = new RemoveMeInfluence(deadbody, cell.getObjects());
		cell.addInfluence(remove);
	}

}
