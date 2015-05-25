package env2.instanciator.bodies.termite;

import env2.body.termitebody.TermiteUndertakerBody;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.type.Time;

public final class TermiteUndertakerInstanciator extends AbstractBodyInstanciator {

	@Override
	public TermiteUndertakerBody getNew() {
		return new TermiteUndertakerBody(Time.TIME, TRIBE_ID, ENV, DIR, POS);
	}

}
