package env2.instanciator.bodies.ant;

import env2.body.antbody.AntMotherBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntMotherInstanciator extends AbstractBodyInstanciator {

	@Override
	public AntMotherBody getNew() {
		return new AntMotherBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}

}
