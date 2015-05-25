package env2.instanciator;

import env2.api.AbstractWorldObject;
import env2.resources.Sugar;

public final class SugarInstanciator extends AbstractInstanciator {

	@Override
	public AbstractWorldObject getNew() {
		return new Sugar(0);
	}
}
