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
}
