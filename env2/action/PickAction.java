package env2.action;

import java.util.List;

import env2.api.AbstractAction;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
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
	protected List<AbstractWorldObject> container;

	
	public PickAction(InterfaceGatherer body, List<AbstractWorldObject> container, AbstractResource resource, int qty) {
		picker = body;
		picked = resource;
		this.qty = -qty;
		if (resource.getQuantity() <= 0)
			container.remove(resource);
	}

	public void doAction() {
		picker.take(picked, qty);
	}
}
