package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Fruit;

public final class FruitInstanciator extends AbstractInstanciator {

	public AbstractWorldObject getNew() {
		return new Fruit(0);
	}
}
