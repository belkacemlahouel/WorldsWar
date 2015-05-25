package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Wood;

public final class WoodInstanciator extends AbstractInstanciator {

	@Override
	public AbstractWorldObject getNew() {
		return new Wood(0);
	}
}
