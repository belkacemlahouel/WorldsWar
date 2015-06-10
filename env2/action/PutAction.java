package env2.action;

import java.util.Collection;

import sim.Simulator;
import env2.api.AbstractAction;
import env2.api.AbstractCell;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
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
			// FIXME We assume the pointed cell is properly set before anything
			AbstractCell cell = Simulator.getPointedCell();
			AbstractResource tmpres = getResourceOfSameType(res, cell.getObjects());
			if (tmpres == null)
				cell.getObjects().add(res);
			else
				tmpres.add(qty);
		} else actor.put(res, qty);
	}
	
	/*
	 * Taken from AntGathererBody...
	 * FIXME
	 */
	private AbstractResource getResourceOfSameType(AbstractResource o, Collection<? extends AbstractWorldObject> container) {
		for (AbstractWorldObject res : container) {
			if (res.getType() == o.getType()) {
				return ((AbstractResource) res);
			}
		}
		
		return null;
	}
}
