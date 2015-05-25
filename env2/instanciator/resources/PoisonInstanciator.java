package env2.instanciator.resources;

import env2.api.AbstractResource;
import env2.resources.Poison;

public final class PoisonInstanciator extends AbstractResourceInstanciator {

	@Override
	public AbstractResource getNew() {
		return new Poison(0);
	}
}
