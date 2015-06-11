package env2.action;

import java.util.List;

import env2.api.AbstractAction;
import env2.api.AbstractBody;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;

public class EatAction extends AbstractAction {
	
	protected AbstractBody eater;
	protected int qty;
	protected AbstractResource resource;
	protected List<AbstractWorldObject> container;
	
	public EatAction(AbstractBody eater, List<AbstractWorldObject> container, AbstractResource resource, int qty) {
		this.qty = qty;
		this.eater = eater;
		this.resource = resource;
		this.container = container;
	}

	public void doAction() {
		AbstractResource newresource = resource.pick(qty);
		eater.applyEffectsForEating(newresource, qty);
		if (resource.getQuantity() <= 0){
			container.remove(resource);
		}else{
			resource.pick(qty);
		}
	}
}
