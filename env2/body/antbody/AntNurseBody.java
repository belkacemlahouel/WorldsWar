package env2.body.antbody;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceNurse;
import env2.type.Direction;

public final class AntNurseBody extends AntBody implements InterfaceNurse {

	public AntNurseBody(AbstractEnvironment e, Direction dir, MyPoint2D pos,
			float TIME) {
		super(e, dir, pos, TIME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cure(AbstractBody o) {
		// TODO Auto-generated method stub
		
	}

}
