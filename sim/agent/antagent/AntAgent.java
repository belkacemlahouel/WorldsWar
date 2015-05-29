package sim.agent.antagent;

import env2.action.influences.MotionInfluence;
import env2.body.antbody.AntBody;
import sim.agent.AbstractAgent;

public abstract class AntAgent extends AbstractAgent {

	public AntAgent(AntBody b, int ID) {
		super(b, ID);
	}

	/**
	 * Here is the whole decision process to implement
	 * Default behavior for all agents controlling this type of body
	 * But this should be overidden when possible
	 */
	public abstract MotionInfluence live();
}
