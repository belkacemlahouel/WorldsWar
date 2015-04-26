package env2.api;

import java.util.Iterator;

public abstract class AbstractFrustrum {
	
	protected AbstractBody b;
	protected AbstractEnvironment e;
	
	public AbstractFrustrum(AbstractBody b, AbstractEnvironment e) {
		this.b = b;
		this.e = e;
	}

	public abstract Iterator<AbstractWorldObject> objects();
}
