package env2.frustrum;

import java.util.Iterator;

import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;

public abstract class AbstractFrustrum2 {

	protected AbstractBody b;
	protected AbstractEnvironment e;
	
	public AbstractFrustrum2(AbstractBody b, AbstractEnvironment e) {
		this.b = b;
		this.e = e;
	}

	public abstract Iterator<AbstractWorldObjectWithPosition> objects();
}
