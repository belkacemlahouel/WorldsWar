package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Poison;

public final class PoisonInstanciator extends AbstractInstanciator {

	@Override
	public AbstractWorldObject getNew() {
		return new Poison(0);
	}
}
