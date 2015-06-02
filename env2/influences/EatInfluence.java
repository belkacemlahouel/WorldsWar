package env2.influences;

import java.util.Collection;

import env2.api.AbstractBody;
import env2.api.AbstractInfluence;
import env2.api.AbstractResource;
import env2.type.InfluenceType;

public class EatInfluence extends AbstractInfluence {

	public AbstractBody eater;
	public int qty;
	public AbstractResource resource;
	public Collection<AbstractResource> container;
	
	public EatInfluence(AbstractBody eater, Collection<AbstractResource> container, AbstractResource resource, int qty) {
		this.qty = qty;
		this.eater = eater;
		this.resource = resource;
		this.container = container;
	}

	@Override
	public InfluenceType getType() {
		return InfluenceType.EAT;
	}
}
