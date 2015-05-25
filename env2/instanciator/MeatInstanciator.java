package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Meat;

public final class MeatInstanciator extends AbstractInstanciator {

	@Override
	public AbstractWorldObject getNew() {
		return new Meat(0);
	}
}
