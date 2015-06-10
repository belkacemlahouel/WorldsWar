package env2.action;

import sim.Simulator;
import env2.api.AbstractAction;
import env2.api.AbstractResource;
import env2.api.InterfaceGatherer;

/**
 * We assume only a body can pick (therefore put) a resource.
 * That's why this action is only implemented for those types.
 * @author belka
 *
 */

public class PutAction extends AbstractAction {

	private InterfaceGatherer actor;
	private AbstractResource res;
	private int qty;
	
	public PutAction(InterfaceGatherer actor, AbstractResource res, int qty) {
		this.actor = actor;
		this.res = res;
		this.qty = qty;
	}
	
	public void doAction() {
		if (actor == null) {
			actor = Simulator.getVirtualGatherer();
			// FIXME We assume the actor is already in the good position
		}
		
		actor.put(res, qty);
	}
}
