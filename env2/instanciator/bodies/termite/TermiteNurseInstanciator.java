package env2.instanciator.bodies.termite;

import env2.body.termitebody.TermiteNurseBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteNurseInstanciator extends AbstractBodyInstanciator {

	@Override
	public TermiteNurseBody getNew() {
		return new TermiteNurseBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}
}
