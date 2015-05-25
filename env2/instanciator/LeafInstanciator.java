package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Leaf;

public final class LeafInstanciator extends AbstractInstanciator {

	@Override
	public AbstractWorldObject getNew() {
		return new Leaf(0);
	}
}
