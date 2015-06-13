package env2.body.antbody;

import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceNurse;
import env2.type.Direction;
import env2.type.WorldObjectType;

public final class AntNurseBody extends AntBody implements InterfaceNurse {
	
	public AntNurseBody(int TIME, int tribe_id, AbstractEnvironment env, Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
	}

	@Override
	public void cure(AbstractBody o) {
		o.applyLifeVariation(getTotCure());
	}

	@Override
	public final int getStdCure() {
		return 7;
	}

	@Override
	public final int getRndCure() {
		return 3;
	}

	@Override
	public final int getTotCure() {
		return getStdCure() + MyMath.random(-getRndCure(), getRndCure());
	}
	
	public WorldObjectType getType() {
		return WorldObjectType.ANTNURSEBODY;
	}
}
