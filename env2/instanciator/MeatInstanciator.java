package env2.instanciator;

import env2.api.AbstractResource;
import env2.resources.Meat;

public final class MeatInstanciator extends AbstractResourceInstanciator {

	@Override
	public AbstractResource getNew() {
		return new Meat(0);
	}
}
