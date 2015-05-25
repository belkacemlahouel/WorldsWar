package env2.instanciator;

import env2.api.AbstractResource;
import env2.resources.Sugar;

public final class SugarInstanciator extends AbstractResourceInstanciator {

	@Override
	public AbstractResource getNew() {
		return new Sugar(0);
	}
}
