package env2.instanciator.bodies.ant;

import sim.agent.antagent.AntUndertakerAgent;
import env2.body.antbody.AntUndertakerBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntUndertakerInstanciator extends AbstractBodyInstanciator {

	private AntUndertakerBody body;
	private AntUndertakerAgent agent;
	
	@Override
	public AntUndertakerBody getNew() {
		body = new AntUndertakerBody(Time.getTime(), TRIBE_ID, ENV, DIR, POS);
		agent = new AntUndertakerAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public AntUndertakerAgent getAgent() {
		return agent;
	}

}
