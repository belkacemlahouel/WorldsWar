package math;

public class Intersection {
	
	public final float time;
	public final MyPoint2D position;
	
	public Intersection(float time, int x, int y) {
		this.time = time;
		position = new MyPoint2D(x, y);
	}
	
	public String toString() {
		return "(in " + time + "s, in" + position.toString() + ")";
	}
}
