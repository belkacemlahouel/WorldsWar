package env2.body.termitebody;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.api.InterfaceGatherer;
import env2.type.Direction;

public final class TermiteGathererBody extends TermiteBody implements InterfaceGatherer {

	public TermiteGathererBody(int TIME, int tribe_id, AbstractEnvironment env, Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void take(AbstractResource o, int qty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(AbstractResource o, int qty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStdTakeQty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRndTakeQty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotTakeQty() {
		// TODO Auto-generated method stub
		return 0;
	}
}
