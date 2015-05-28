package sim.agent;

import env2.api.AbstractBody;
import env2.api.AbstractInfluence;
import env2.type.Time;

public abstract class AbstractAgent {
	
	protected final int TRIBE_ID;
	
	protected AbstractBody body;

	public AbstractAgent(AbstractBody b, int ID) {
		body = b;
		TRIBE_ID = ID;
	}
	
	/**
	 * Getter for the body
	 * @return body to which this agent is attached
	 */
	public AbstractBody getBody() {
		return body;
	}
	
	/**
	 * Decision process implemented here
	 * The agent applies influences
	 */
	public abstract AbstractInfluence live();
	
	/**
	 * Decision process inside
	 * There is this default version, which can be overidden if need be
	 * @return boolean: should the agent kill himself?
	 */
	public boolean killMe() {
		if (body.getLife() < 0 || body.getAge(Time.TIME) > body.getMaxAge())
			return true;
		
		return false;
	}
	
	/**
	 * So agents can know if they belong in the same team or not
	 * @return tribe id: tribe index
	 */
	public int getTribeID() {
		return TRIBE_ID;
	}
}