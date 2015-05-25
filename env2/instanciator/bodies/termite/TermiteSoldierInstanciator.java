package env2.instanciator.bodies.termite;

import sim.agent.termiteagent.TermiteSoldierAgent;
import env2.body.termitebody.TermiteSoldierBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteSoldierInstanciator extends AbstractBodyInstanciator {

	private TermiteSoldierBody body;
	private TermiteSoldierAgent agent;
	
	@Override
	public TermiteSoldierBody getNew() {
		body = new TermiteSoldierBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
		agent = new TermiteSoldierAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public TermiteSoldierAgent getAgent() {
		return agent;
	}

}
