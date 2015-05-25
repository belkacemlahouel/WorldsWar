package env2.instanciator.bodies;

import env2.api.AbstractBody;
import env2.instanciator.AbstractInstanciator;

public abstract class AbstractBodyInstanciator extends AbstractInstanciator {
	
	public abstract AbstractBody getNew();
}
