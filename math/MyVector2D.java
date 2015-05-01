package math;

public class MyVector2D {

	private float dx, dy;
	
	/**
	 * constructor takes two parameters
	 * delta in x and y
	 */
	
	public MyVector2D(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * construction of the vector ab
	 */
	
	public MyVector2D(MyPoint2D a, MyPoint2D b) {
		new MyVector2D(b.getX()-a.getX(), b.getY()-a.getY());
	}
	
	/**
	 * classic getter
	 */
	
	public float getDx() {
		return dx;
	}
	
	/**
	 * classic getter
	 */
	
	public float getDy() {
		return dy;
	}
	
	/**
	 * classic dot product between this and w
	 */
	
	public float dot(MyVector2D w) {
		return dx*w.dx + dy*w.dy;
	}
	
	/**
	 * classic length
	 * use lengthSquared if enough
	 */
	
	public float length() {
		return (float) Math.sqrt(dx*dx + dy*dy);
	}
	
	/**
	 * length squared, to avoid useless Math.sqrt
	 * useful when comparing
	 */
	
	public float lengthSquared() {
		return dx*dx + dy*dy;
	}
	
	/**
	 * in place normalization
	 */
	
	public void normalize() {
		final float LENGTH = length(); 
		dx /= LENGTH;
		dy /= LENGTH;
	}
	
	/**
	 * normalizes and returns a new normalized vector
	 */
	
	public MyVector2D normalized() {
		final float LENGTH = length();
		return new MyVector2D(dx/LENGTH, dy/LENGTH);
	}
	
	/**
	 * in place discretization for the floating vector
	 */
	
	public void discretize() {
		dx = (int) dx;
		dy = (int) dy;
	}
	
	/**
	 * discretizes and returns a new discretized vector
	 */
	
	public MyVector2D discretized() {
		return new MyVector2D((int) dx, (int) dy);
	}
	
	/**
	 * angle that this vector forms with (0, xaxis)
	 * in RADIANS in ]-pi, pi]
	 */
	
	public float angle() {
		return (float) Math.atan2(dy, dx);
	}
	
	/**
	 * classic toString override
	 */
	
	public String toString() {
		return "(" + dx + ", " + dy + ")";
	}
	
	/**
	 * plus operation for vectors
	 */
	
	public MyVector2D plus(MyVector2D w) {
		return new MyVector2D(dx+w.dx, dy+w.dy);
	}
	
	/**
	 * minus operation for vectors
	 */
	
	public MyVector2D minus(MyVector2D w) {
		return new MyVector2D(dx-w.dx, dy-w.dy);
	}
	
	/**
	 * scalar multiplication (scaling) for vectors
	 */
	
	public MyVector2D times(float lambda) {
		return new MyVector2D(lambda*dx, lambda*dy);
	}
	
	/**
	 * classic equality test
	 */
	
	public boolean equals(MyVector2D w) {
		return w.dx == dx && w.dy == dy;
	}
	
	/**
	 * Main test function
	 */
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		MyVector2D ox = new MyVector2D(-1, 1);
		
		System.out.println(ox.angle() + " " + 3*Math.PI/4);
		
		System.out.println("Bye World!");
	}
}
