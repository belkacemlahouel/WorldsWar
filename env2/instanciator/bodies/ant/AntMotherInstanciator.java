package env2.instanciator.bodies.ant;

import sim.agent.antagent.AntMotherAgent;
import env2.body.antbody.AntMotherBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntMotherInstanciator extends AbstractBodyInstanciator {

	private AntMotherBody body;
	private AntMotherAgent agent;
	
	@Override
	public AntMotherBody getNew() {
		body = new AntMotherBody(Time.getTime(), TRIBE_ID, ENV, DIR, POS);
		agent = new AntMotherAgent(body, TRIBE_ID, AbstractBodyInstanciator.getMothers());
		return body;
	}

	@Override
	public AntMotherAgent getAgent() {
		return agent;
	}
}
