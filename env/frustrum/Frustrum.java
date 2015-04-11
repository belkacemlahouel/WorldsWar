package env.frustrum;

import java.util.Iterator;

import env.WorldObject;
import env.body.Body;
import env.environment.Environment;

public abstract class Frustrum {
	
	protected Body b;
	protected Environment e;
	
	public Frustrum(Body b, Environment e) {
		this.b = b;
		this.e = e;
	}

	public abstract Iterator<WorldObject> objects();
}
