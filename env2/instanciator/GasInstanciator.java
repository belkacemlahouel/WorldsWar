package env2.instanciator;

import env2.api.AbstractResource;
import env2.resources.Gas;

public final class GasInstanciator extends AbstractResourceInstanciator {
	
	@Override
	public AbstractResource getNew() {
		return new Gas(0);
	}
}
