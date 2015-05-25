package env2.instanciator.resources;

import env2.api.AbstractResource;
import env2.instanciator.AbstractInstanciator;

public abstract class AbstractResourceInstanciator extends AbstractInstanciator {

	public abstract AbstractResource getNew();
}
