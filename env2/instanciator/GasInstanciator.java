package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Gas;

public final class GasInstanciator extends AbstractInstanciator {
	
	@Override
	public AbstractWorldObject getNew() {
		return new Gas(0);
	}
}
