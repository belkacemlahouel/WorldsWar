package env2.instanciator.bodies.termite;

import env2.body.termitebody.TermiteGathererBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteGathererInstanciator extends AbstractBodyInstanciator {

	@Override
	public TermiteGathererBody getNew() {
		return new TermiteGathererBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}
}
