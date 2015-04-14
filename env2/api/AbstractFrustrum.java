package env2.api;

import java.util.Iterator;

import env.WorldObject;
import env.body.Body;
import env.environment.Environment;

public abstract class AbstractFrustrum {
	
	protected Body b;
	protected Environment e;
	
	public AbstractFrustrum(Body b, Environment e) {
		this.b = b;
		this.e = e;
	}

	public abstract Iterator<WorldObject> objects();
}
