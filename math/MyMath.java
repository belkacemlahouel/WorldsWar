package math;


import env.Direction;

public class MyMath {

	public static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}
	
	public static double manhattanDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(y2-y1) + Math.abs(x2-x1);
	}
	
	public static Direction deltaPosToDirection(MyPoint2D start, MyPoint2D end) {
		if (end.getX() - start.getX() > 0) {
			return Direction.EAST;
		} else if (end.getX() - start.getX() < 0) {
			return Direction.WEST;
		} else {
			if (end.getY() - start.getY() > 0) {
				return Direction.NORTH;
			} else {
				return Direction.SOUTH;
			}
		}
	}
	
	public static int clamp(int x, int min, int max){
		if(x >= max)	return max;
		if(x <= min)	return min;
		
		return x;
	}
	
	public static double clamp(double x, double min, double max){
		if(x >= max)	return max;
		if(x <= min)	return min;
		
		return x;
	}
	
	
	
	
	/**
	 * @param pos1 position of the first item
	 * @param vel1 velocity of the first item
	 * @param pos2 position of the second item
	 * @param vel2 velocity of the second item
	 * 
	 * --- Discussion of the result
	 * if v1 == v2,
	 * 		if pos1 == pos2 then the intersection occurs now
	 * 		else there is no intersection
	 * else t = (pos2 - pos1) / (v1 - v2)
	 *  
	 * @return intersection as a couple (time, position)
	 * where time is the remaining time before intersection
	 * 		position is the position of intersection
	 */
	
	public static Intersection intersection(MyPoint2D pos1, MyVector2D vel1, MyPoint2D pos2, MyVector2D vel2) {
		if (pos2.equals(pos1))
			return new Intersection(0, pos1.getX(), pos1.getY());
		else {
			if (vel1.equals(vel2))
				return null;
			else {
				final float TX, TY, DX, DY;
				final int X, Y;
				
				X = (int) (pos2.getX() - pos1.getX());
				Y = (int) (pos2.getY() - pos1.getY());
				DX = vel1.getDx() - vel2.getDx();
				DY = vel1.getDy() - vel2.getDy();
				
				TX = X/DX;
				TY = Y/DY;
				
				System.err.println("TX == TY ? " + (TX == TY));
				// We just need to check if we have the same result whatever what coordinate we use for this.
				
				return new Intersection(TX, X, Y);
			}
		}
	}
	
	/**
	 * test method
	 */
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Intersection res = MyMath.intersection(new MyPoint2D(-1, 0),
												new MyVector2D(1, 0),
												new MyPoint2D(0, -1),
												new MyVector2D(0, 1)); // TODO
		System.out.println("Bye World!");
	}
}
