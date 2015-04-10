package env;

import math.MyPoint2D;

public abstract class Body extends WorldObject implements Movable {

	private Environment env;
	private MyPoint2D pos;
	private Frustrum f;
	private Direction dir;
	
	public Body(Environment env, Direction dir) {
		this.env = env;
		pos = null;
		this.dir = dir;
	}
	
	/**
	 * @return viewing direction only
	 */
	public Direction getDirection() {
		return dir;
	}
	
	/**
	 * @return current position
	 */
	public MyPoint2D getPosition() {
		return pos;
	}
	
	/**
	 * @param pos: new position of the body
	 */
	public void setPosition(MyPoint2D pos) {
		this.pos = pos;
	}
	
	/**
	 * @return current frustrum
	 */
	public Frustrum getFrustrum() {
		return f;
	}
	
	/***/
	
	public abstract void newFrustrum();
	
	public abstract void move(MyPoint2D end);
	
	public abstract int getLife();
	public abstract int getCapacity();
	public abstract int getStrength();
	public abstract int getSpeed();
	public abstract int getPerceptionDistance();
	// public abstract int putInformation();
	public abstract int getMoveDistance();
}
