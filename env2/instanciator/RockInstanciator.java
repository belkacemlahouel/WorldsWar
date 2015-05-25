package env2.instanciator;

import env2.api.AbstractResource;
import env2.resources.Rock;

public final class RockInstanciator extends AbstractResourceInstanciator {

	@Override
	public AbstractResource getNew() {
		return new Rock(0);
	}
}
