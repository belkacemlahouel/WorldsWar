package sim;

import env.Body;

public abstract class Agent {

	public Agent(Body b) {
		
	}
	
	public abstract Body getBody();
	public abstract void play();
}