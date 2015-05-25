package env2.instanciator.bodies.ant;

import env2.body.antbody.AntGathererBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntGathererInstanciator extends AbstractBodyInstanciator {

	@Override
	public AntGathererBody getNew() {
		return new AntGathererBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}
}
