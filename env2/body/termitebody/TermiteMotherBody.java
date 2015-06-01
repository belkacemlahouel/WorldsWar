package env2.body.termitebody;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceMother;
import env2.type.Direction;

public final class TermiteMotherBody extends TermiteBody implements InterfaceMother {

	public TermiteMotherBody(int TIME, int tribe_id, AbstractEnvironment env,
			Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addBaby() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getTribeID() {
		return _getTribeID();
	}
	
	@Override
	public boolean equals(InterfaceMother that) {
		return this == that;
	}
	
	@Override
	public int hashCode() {
		return _getTribeID();
	}
}
