package env2.instanciator.bodies.ant;

import env2.body.antbody.AntSoldierBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntSoldierInstanciator extends AbstractBodyInstanciator {

	@Override
	public AntSoldierBody getNew() {
		return new AntSoldierBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}

}
