package env2.body.antbody;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.InterfaceUndertaker;
import env2.type.Direction;

public final class AntUndertakerBody extends AntBody implements InterfaceUndertaker {

	public AntUndertakerBody(int TIME, int tribe_id, AbstractEnvironment env, Direction dir, MyPoint2D pos) {
		super(TIME, tribe_id, env, dir, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buryDead(AbstractBody b) {
		// TODO Auto-generated method stub
		
	}
}
