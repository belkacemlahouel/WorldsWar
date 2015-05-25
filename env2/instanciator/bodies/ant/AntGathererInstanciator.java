package env2.instanciator.bodies.ant;

import sim.agent.antagent.AntGathererAgent;
import env2.body.antbody.AntGathererBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntGathererInstanciator extends AbstractBodyInstanciator {

	private AntGathererBody body;
	private AntGathererAgent agent;
	
	@Override
	public AntGathererBody getNew() {
		body = new AntGathererBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
		agent = new AntGathererAgent(body, TRIBE_ID);
		return body;
	}
	
	@Override
	public AntGathererAgent getAgent() {
		return agent;
	}
}
