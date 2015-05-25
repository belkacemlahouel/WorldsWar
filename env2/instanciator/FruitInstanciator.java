package env2.instanciator;

import env2.api.AbstractResource;
import env2.resources.Fruit;

public final class FruitInstanciator extends AbstractResourceInstanciator {

	public AbstractResource getNew() {
		return new Fruit(0);
	}
}
