package math;


public class MyPoint2D implements Comparable<MyPoint2D> {
		
	private int x, y;
	
	public MyPoint2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
	
	public void add(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * plus operation with a vector
	 * be careful this is a vector of integers
	 */
	
	public MyPoint2D plus(MyVector2D w) {
		return new MyPoint2D((int) w.getDx() + x, (int) w.getDy() + y);
	}
	
	/**
	 * minus operation with a point
	 * be careful this is a vector of integers
	 */
	
	public MyPoint2D minus(MyVector2D w) {
		return new MyPoint2D((int) -w.getDx() + x, (int) -w.getDy() + y);
	}

	public int compareTo(MyPoint2D o) {
		if (o.x == x) {
			if (y > o.y) return 1;
			if (y < o.y) return -1;
		}
		
		if (x > o.x) return 1;
		if (x < o.x) return -1;
		
		return 0;
	}
	
	public static MyPoint2D random(int width, int height) {
		int randx, randy;
		randx = (int) (Math.random() * width);
		randy = (int) (Math.random() * height);
		return new MyPoint2D(randx, randy);
	}
}
