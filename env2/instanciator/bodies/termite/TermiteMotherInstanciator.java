package env2.instanciator.bodies.termite;

import sim.agent.termiteagent.TermiteMotherAgent;
import env2.body.termitebody.TermiteMotherBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteMotherInstanciator extends AbstractBodyInstanciator {

	private TermiteMotherBody body;
	private TermiteMotherAgent agent;
	
	@Override
	public TermiteMotherBody getNew() {
		body = new TermiteMotherBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
		agent = new TermiteMotherAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public TermiteMotherAgent getAgent() {
		return agent;
	}
}
