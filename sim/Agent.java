package sim;

import env.body.Body;

public abstract class Agent {

	public Agent(Body b) {
		
	}
	
	public abstract Body getBody();
	public abstract void live();
	public abstract boolean killMe();
	
	/* do they know in which team they belong? */
}