package sim.agent.termiteagent;

import env2.body.termitebody.TermiteBody;
import env2.influences.MotionInfluence;
import sim.agent.AbstractAgent;

public abstract class TermiteAgent extends AbstractAgent {

	public TermiteAgent(TermiteBody b, int ID) {
		super(b, ID);
	}

	/**
	 * Here is the whole decision process to implement
	 * Default behavior for all agents controlling this type of body
	 * But this should be overidden when possible
	 */
	public abstract MotionInfluence live();
}
