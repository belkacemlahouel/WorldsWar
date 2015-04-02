package env;

import math.MyPoint2D;

public abstract class Body extends WorldObject implements Movable {

	private Environment env;
	private Direction dir;
	private MyPoint2D pos;
	private Frustrum f;
	
	public Body(Environment env, Direction dir) {
		this.env = env;
		this.dir = dir;
		pos = null;
	}
	
	public Direction getDirection() {
		return dir;
	}
	
	public void move(Direction dir) {
		env.move(this, dir);
		this.dir = dir;
	}
	
	public MyPoint2D getPosition() {
		return pos;
	}
	
	public void setPosition(MyPoint2D pos) {
		this.pos = pos;
	}
	
	public Frustrum getFrustrum() {
		return f;
	}
	
	public void newFrustrum() {
		f = new FrustrumCircle(this, env);
	}
}
