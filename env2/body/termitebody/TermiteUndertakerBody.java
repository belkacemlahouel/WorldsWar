package env2.body.termitebody;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceUndertaker;
import env2.type.Direction;

public class TermiteUndertakerBody extends TermiteBody implements InterfaceUndertaker {

	public TermiteUndertakerBody(AbstractEnvironment e, Direction dir,
			MyPoint2D pos, float TIME) {
		super(e, dir, pos, TIME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buryDead(AbstractBody b) {
		// TODO Auto-generated method stub
		
	}

}
