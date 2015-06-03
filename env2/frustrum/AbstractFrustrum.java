package env2.frustrum;

import java.util.Iterator;

import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;

public abstract class AbstractFrustrum {

	protected AbstractBody b;
	protected AbstractEnvironment e;
	
	public AbstractFrustrum(AbstractBody b, AbstractEnvironment e) {
		this.b = b;
		this.e = e;
	}

	public abstract Iterator<Perception> objects();
}
