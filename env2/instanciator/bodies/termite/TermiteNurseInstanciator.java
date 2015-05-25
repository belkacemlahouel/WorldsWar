package env2.instanciator.bodies.termite;

import sim.agent.termiteagent.TermiteNurseAgent;
import env2.body.termitebody.TermiteNurseBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteNurseInstanciator extends AbstractBodyInstanciator {

	private TermiteNurseBody body;
	private TermiteNurseAgent agent;
	
	@Override
	public TermiteNurseBody getNew() {
		body = new TermiteNurseBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
		agent = new TermiteNurseAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public TermiteNurseAgent getAgent() {
		return agent;
	}
}
