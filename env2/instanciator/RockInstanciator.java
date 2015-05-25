package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Rock;

public final class RockInstanciator extends AbstractInstanciator {

	@Override
	public AbstractWorldObject getNew() {
		return new Rock(0);
	}
}
