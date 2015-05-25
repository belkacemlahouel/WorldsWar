package env2.instanciator.resources;

import env2.api.AbstractResource;
import env2.resources.Wood;

public final class WoodInstanciator extends AbstractResourceInstanciator {

	@Override
	public AbstractResource getNew() {
		return new Wood(0);
	}
}
