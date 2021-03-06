package env2.frustrum.old;

import java.util.Iterator;

import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;

public abstract class OldAbstractFrustrum {
	
	protected AbstractBody b;
	protected AbstractEnvironment e;
	
	public OldAbstractFrustrum(AbstractBody b, AbstractEnvironment e) {
		this.b = b;
		this.e = e;
	}

	public abstract Iterator<AbstractWorldObject> objects();
}
