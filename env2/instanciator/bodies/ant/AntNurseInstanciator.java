package env2.instanciator.bodies.ant;

import env2.body.antbody.AntNurseBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntNurseInstanciator extends AbstractBodyInstanciator {

	@Override
	public AntNurseBody getNew() {
		return new AntNurseBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}
}
