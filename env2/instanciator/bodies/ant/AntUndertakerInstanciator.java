package env2.instanciator.bodies.ant;

import env2.body.antbody.AntUndertakerBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class AntUndertakerInstanciator extends AbstractBodyInstanciator {

	@Override
	public AntUndertakerBody getNew() {
		return new AntUndertakerBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}

}
