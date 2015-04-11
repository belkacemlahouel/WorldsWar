package env.body;

import env.Direction;
import env.Environment;
import math.MyPoint2D;

public class AntBody extends Body {

	public AntBody(Environment env, Direction dir) {
		super(env, dir);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(MyPoint2D end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStrength() {
		return 10;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getPerceptionDistance() {
		return 3;
	}

	@Override
	public int getMoveDistance() {
		return 1;
	}

	@Override
	public String toString() {
		return "ANT";
	}

	@Override
	public void newFrustrum() {
		// TODO Auto-generated method stub
		
	}

}
