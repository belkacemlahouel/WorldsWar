package env2.instanciator;

import env2.api.AbstractResource;
import env2.resources.Leaf;

public final class LeafInstanciator extends AbstractResourceInstanciator {

	@Override
	public AbstractResource getNew() {
		return new Leaf(0);
	}
}
