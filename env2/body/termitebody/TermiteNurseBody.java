package env2.body.termitebody;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceNurse;
import env2.type.Direction;

public final class TermiteNurseBody extends TermiteBody implements InterfaceNurse {

	public TermiteNurseBody(int TIME, int tribe_id, AbstractEnvironment env,
			Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cure(AbstractBody o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStdCure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRndCure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotCure() {
		// TODO Auto-generated method stub
		return 0;
	}

}
