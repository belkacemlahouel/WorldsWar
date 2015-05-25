package env2.instanciator.bodies.termite;

import env2.body.termitebody.TermiteSoldierBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteSoldierInstanciator extends AbstractBodyInstanciator {

	@Override
	public TermiteSoldierBody getNew() {
		return new TermiteSoldierBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}

}
