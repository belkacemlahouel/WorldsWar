package env2.body.termitebody;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceSoldier;
import env2.type.Direction;

public final class TermiteSoldierBody extends TermiteBody implements InterfaceSoldier {

	public TermiteSoldierBody(int TIME, int tribe_id, AbstractEnvironment env,
			Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(AbstractBody o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStdDmg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRndDmg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotDmg() {
		// TODO Auto-generated method stub
		return 0;
	}

}
