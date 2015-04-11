package env.environment;

import math.MyPoint2D;
import env.api.Movable;

public class EnvironmentGround extends Environment {

	public EnvironmentGround(int width, int height) {
		super(width, height);
	}

	@Override
	public void applyInfluences() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void solveConflicts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Movable o, MyPoint2D end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printEnvironment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean movePossible(Movable o, MyPoint2D end) {
		// TODO Auto-generated method stub
		return false;
	}

}
