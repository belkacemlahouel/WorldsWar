package sim;

import env2.api.AbstractBody;

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
	public abstract void live();
	
	/**
	 * Decision process inside
	 * @return boolean: should the agent kill himself?
	 */
	public abstract boolean killMe();
	
	/**
	 * So agents can know if they belong in the same team or not
	 * @return tribe id: tribe index
	 */
	public int getTribeID() {
		return TRIBE_ID;
	}
}