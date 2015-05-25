package env2.instanciator.bodies.spider;

import env2.body.spider.SpiderBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public class SpiderInstanciator extends AbstractBodyInstanciator {

	@Override
	public SpiderBody getNew() {
		return new SpiderBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}

}
