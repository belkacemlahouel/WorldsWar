package env2.instanciator.bodies.termite;

import sim.agent.termiteagent.TermiteUndertakerAgent;
import env2.body.termitebody.TermiteUndertakerBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteUndertakerInstanciator extends AbstractBodyInstanciator {

	private TermiteUndertakerBody body;
	private TermiteUndertakerAgent agent;
	
	@Override
	public TermiteUndertakerBody getNew() {
		body = new TermiteUndertakerBody(Time.getTime(), TRIBE_ID, ENV, DIR, POS);
		agent = new TermiteUndertakerAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public TermiteUndertakerAgent getAgent() {
		return agent;
	}

}
