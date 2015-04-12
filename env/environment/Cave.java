package env.environment;

import math.MyPoint2D;
import env.Cell;
import env.api.Movable;

public class Cave extends Environment {
	
	public Cave(int width, int height, Cell inGround) {
		super(width, height);
		// this.inGround = inGround;
		
		int rX = (int) (Math.random() * width);
		int rY = (int) (Math.random() * height);
		// portal = getCell(rX, rY);
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
