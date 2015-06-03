package env2.instanciator.bodies.ant;

import sim.agent.antagent.AntSoldierAgent;
import env2.body.antbody.AntSoldierBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntSoldierInstanciator extends AbstractBodyInstanciator {

	private AntSoldierBody body;
	private AntSoldierAgent agent;
	
	@Override
	public AntSoldierBody getNew() {
		body = new AntSoldierBody(Time.getTime(), TRIBE_ID, ENV, DIR, POS);
		agent = new AntSoldierAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public AntSoldierAgent getAgent() {
		return agent;
	}

}
