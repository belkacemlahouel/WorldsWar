package env2.instanciator.bodies.ant;

import sim.agent.antagent.AntNurseAgent;
import env2.body.antbody.AntNurseBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Direction;
import env2.type.Time;

public final class AntNurseInstanciator extends AbstractBodyInstanciator {

	private AntNurseBody body;
	private AntNurseAgent agent;
	
	@Override
	public AntNurseBody getNew() {
		DIR = Direction.random();
		body = new AntNurseBody(Time.getTime(), TRIBE_ID, ENV, DIR, POS);
		agent = new AntNurseAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public AntNurseAgent getAgent() {
		return agent;
	}
}
