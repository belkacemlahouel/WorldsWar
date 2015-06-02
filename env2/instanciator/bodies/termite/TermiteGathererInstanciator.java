package env2.instanciator.bodies.termite;

import sim.agent.termiteagent.TermiteGathererAgent;
import env2.body.termitebody.TermiteGathererBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteGathererInstanciator extends AbstractBodyInstanciator {

	private TermiteGathererBody body;
	private TermiteGathererAgent agent;
	
	@Override
	public TermiteGathererBody getNew() {
		body = new TermiteGathererBody(Time.getTime(), TRIBE_ID, ENV, DIR, POS);
		agent = new TermiteGathererAgent(body, TRIBE_ID);
		return body;
	}
	
	@Override
	public TermiteGathererAgent getAgent() {
		return agent;
	}
}
