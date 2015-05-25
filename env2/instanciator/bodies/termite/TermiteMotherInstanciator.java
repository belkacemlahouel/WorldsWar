package env2.instanciator.bodies.termite;

import env2.body.termitebody.TermiteMotherBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteMotherInstanciator extends AbstractBodyInstanciator {

	@Override
	public TermiteMotherBody getNew() {
		return new TermiteMotherBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}

}
