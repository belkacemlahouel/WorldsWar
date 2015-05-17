package env2.body.antbody;

import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceSoldier;
import env2.type.Direction;

public final class AntSoldierBody extends AntBody implements InterfaceSoldier {

	public AntSoldierBody(int TIME, int tribe_id, AbstractEnvironment env, Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
	}

	@Override
	public void hit(AbstractBody o) {
		o.applyLifeVariation(-getTotDmg());
	}

	@Override
	public int getStdDmg() {
		return getStrength() * 3;
	}

	@Override
	public int getRndDmg() {
		return 2;
	}

	@Override
	public int getTotDmg() {
		return getStdDmg() + MyMath.random(-getRndDmg(), getRndDmg());
	}
}
