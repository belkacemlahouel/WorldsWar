package env2.instanciator.bodies.spider;

import sim.agent.spideragent.SpiderAgent;
import env2.body.spider.SpiderBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public class SpiderInstanciator extends AbstractBodyInstanciator {

	private SpiderBody body;
	private SpiderAgent agent;
	
	@Override
	public SpiderBody getNew() {
		body = new SpiderBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
		agent = new SpiderAgent(body, TRIBE_ID);
		return body;
	}

	@Override
	public SpiderAgent getAgent() {
		return agent;
	}

}
