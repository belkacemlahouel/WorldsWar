package sim.agent.termiteagent;

import env2.body.termitebody.TermiteBody;
import sim.agent.AbstractAgent;

public class TermiteAgent extends AbstractAgent {

	public TermiteAgent(TermiteBody b, int ID) {
		super(b, ID);
	}

	/**
	 * Here is the whole decision process to implement
	 * Default behavior for all agents controlling this type of body
	 * But this should be overidden when possible
	 */
	public void live() {
		// TODO
	}
}
