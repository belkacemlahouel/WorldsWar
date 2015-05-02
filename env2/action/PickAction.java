package env2.action;

import env2.api.AbstractAction;
import env2.api.AbstractResource;
import env2.api.InterfaceGatherer;

/**
 * We assume only a body can pick a resource.
 * Therefore, this action is available only for those types.
 * @author belka
 *
 */

public class PickAction extends AbstractAction {

	private InterfaceGatherer picker;
	private AbstractResource picked;
	private int qty;
	
	public PickAction(InterfaceGatherer body, AbstractResource resource, int qty) {
		picker = body;
		picked = resource;
		this.qty = qty;
	}

	public void doAction() {
		picker.take(picked, qty);
	}
}
