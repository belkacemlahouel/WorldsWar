package math;


import java.util.ArrayList;
import java.util.List;
import env2.type.Direction;

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
	
	public static int clamp(int x, int min, int max) {
		if(x >= max)		return max;
		else if(x <= min)	return min;
		
		return x;
	}
	
	public static double clamp(double x, double min, double max) {
		if(x >= max)		return max;
		else if(x <= min)	return min;
		
		return x;
	}

	public static List<MyPoint2D> bresenheimLine(MyPoint2D start, MyPoint2D end) {
		return bresenheimLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	public static List<MyPoint2D> bresenheimLine(int startX, int startY, int endX, int endY) {		
		ArrayList<MyPoint2D> linePoints = new ArrayList<MyPoint2D>();
		
		/* Dimensions of the line */
		int width = endX - startX;
	    int height = endY - startY;

		/* How will we move ? */
	    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	    
	    if (width<0)	    	dx1 = dx2 = -1; 
	    else if (width>0)     	dx1 = dx2 =  1;
	    
	    if (height<0)	    	dy1 = -1; 
	    else if (height>0)     	dy1 =  1;
	    
	    /* We suppose that the width > height */
	    int longest = Math.abs(width);
	    int shortest = Math.abs(height);

	    /* If this is false ... */
	    if (!(longest>shortest)) {
	        longest = Math.abs(height);
	        shortest = Math.abs(width);
	        
		    if (height<0)	    	dy2 = -1; 
		    else if (height>0)     	dy2 =  1;

	        dx2 = 0;            
	    }
	    
	    /* "numerator = longest >> 1"
	     * Technically it means numerator is equal to half of longest, 
	     * and is important to avoid y from being rounded at 
	     * every whole number instead of halfway point.
	     */
	    int numerator = longest >> 1;
		
		/* Now we just iterate to find all the points */
		int currentX = startX, currentY = startY;
	    for (int i=0; i<=longest; i++) {
	    	linePoints.add(new MyPoint2D(currentX, currentY));
	    	
	        numerator += shortest;
	        if (!(numerator<longest)) {
	            numerator -= longest;
	            
	            currentX += dx1;
	            currentY += dy1;
	        } 
	        else {
	        	currentX += dx2;
	        	currentY += dy2;
	        }
	    }
	    
		return linePoints;		
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
	 * allows us to check if (posX, posY) is in [0, MAX_X[ x [0, MAX_Y[
	 */
	public static boolean isIn(int posX, int posY, int MAX_X, int MAX_Y) {
		return posX >= 0 && posX < MAX_X &&
				posY >= 0 && posY < MAX_Y;
	}
	
	/**
	 * test method
	 */
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Intersection res = MyMath.intersection(new MyPoint2D(-1, 0),
												new MyVector2D(1, 0),
												new MyPoint2D(0, -1),
												new MyVector2D(0, 1));
		System.out.println("Intersection: " + res);
		System.out.println("Bye World!");
	}
}
