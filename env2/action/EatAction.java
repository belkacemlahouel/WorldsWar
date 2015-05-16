package env2.action;

import java.util.Collection;

import env2.api.AbstractBody;
import env2.api.AbstractResource;

public class EatAction {
	
	protected AbstractBody eater;
	protected int qty;
	protected AbstractResource resource;
	protected Collection<AbstractResource> container;
	
	public EatAction(AbstractBody eater, Collection<AbstractResource> container, AbstractResource resource, int qty) {
		this.qty = qty;
		this.eater = eater;
		this.resource = resource;
		this.container = container;
	}

	public void doAction() {
		eater.eat(resource, qty);
		if (resource.getQuantity() <= 0)
			container.remove(resource);
	}
}
