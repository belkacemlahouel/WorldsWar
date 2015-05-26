package math;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

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
	 * Find the intersection point of two bresenheim lines.
	 * @param firstLine, a bresenheim line.
	 * @param secondLine, a bresenheim line.
	 * @return the point of intersection, a MyPoint2D.
	 */
	public static MyPoint2D intersectionBresenheimLine(List<MyPoint2D> firstLine, List<MyPoint2D> secondLine){
		MyPoint2D intersection;
		Intersection result;
		
		MyPoint2D firstPointFirstLine;
		MyPoint2D lastPointFirstLine = new MyPoint2D(0,0);
		MyPoint2D firstPointSecondLine;
		MyPoint2D lastPointSecondLine = new MyPoint2D(0,0);
		
		MyVector2D vectorFirstLine;
		MyVector2D vectorSecondLine;
		
		/* Catch the first points and last points of the bresenheim lines */
		ListIterator<MyPoint2D> iterator = firstLine.listIterator();
		firstPointFirstLine = iterator.next();
		while (iterator.hasNext())
		{
			lastPointFirstLine=iterator.next();
		}
		
		iterator = secondLine.listIterator();
		firstPointSecondLine = iterator.next();
		while (iterator.hasNext())
		{
			lastPointSecondLine=iterator.next();
		}
		
		/* Create vector following the bresenheim lines */
		vectorFirstLine = new MyVector2D(firstPointFirstLine, lastPointFirstLine);
		vectorSecondLine = new MyVector2D(firstPointSecondLine, lastPointSecondLine);
		
		/* Calculate the intersection of the two vector */
		result = intersection(firstPointFirstLine, vectorFirstLine, firstPointSecondLine, vectorSecondLine);

		if(result != null)
		{
			intersection = result.position;
		}
		else
		{
			intersection = null;
		}
		
		return intersection;
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
			return new Intersection(0f, pos1.getX(), pos1.getY());
		else {
			if((vel1.getDx()/vel2.getDx()) == (vel1.getDy()/vel2.getDy()))
				/* The vector are colinear : There is no intersection. */
				return null;
			else {
				float DX, DY;
				float TX, TY;
				final int X, Y;
				int intersectX, intersectY;
				
				X = (pos2.getX() - pos1.getX());
				Y = (pos2.getY() - pos1.getY());
				DX = vel1.getDx() - vel2.getDx();
				DY = vel1.getDy() - vel2.getDy();
				
				/* Case of denominator equals to zero*/
				if(DX==0){
					TX=0;
				}
				else{
					TX = X/DX;
				}
				
				// Intersection I1 = new Intersection(TX, (int) (TX*vel1.getDx()+pos1.getX()), (int) (TX*vel1.getDy()+pos1.getY()));
				
				// if (TX != TY) return null;
				
				// return I1;

				/* Case of denominator equals to zero*/
				if(DY==0){
					TY=0;
				}
				else{
					TY = Y/DY;
				}
				
				/* Numerator equals to zero still verifies the equation*/
				if(TX==0){
					TX = TY;
				}
				else if(TY==0){
					TY = TX;
				}
				System.out.println(TX +"-"+TY);
				System.err.println("TX == TY ? " + (TX == TY));
				// We just need to check if we have the same result whatever what coordinate we use for this.

				intersectX = (int) (vel1.getDx()*TX + pos1.getX());
				intersectY = (int) (vel1.getDy()*TX + pos1.getY());
				
				return new Intersection(TX, intersectX, intersectY);
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
	 * random number generation using Knuth implementation in Java libraries
	 * uniform distribution
	 * since the method returns integers, there might be probability glitches...
	 * @param min bound (inclusive)
	 * @param max bound (exclusive)
	 * @return random number in [min, max[
	 */
	public static int random(int min, int max) {
		if ((max - min) == 0) return min;
		return new Random().nextInt(max-min) + min;
	}
	
	/**
	 * test method
	 */
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		Intersection res = MyMath.intersection(new MyPoint2D(-2, 0),
												new MyVector2D(1, 0),
												new MyPoint2D(0, -1),
												new MyVector2D(0, 1/2));
		
//		res = MyMath.intersection(new MyPoint2D(0, 1),
//								new MyVector2D(5, 6),
//								new MyPoint2D(5, 0),
//								new MyVector2D(-5, 5));

		res = MyMath.intersection(new MyPoint2D(0, 1),
									new MyVector2D(5, 6),
									new MyPoint2D(5, 0),
									new MyVector2D(-5, 5));

		System.out.println("Intersection: " + res);
		
		List<MyPoint2D> line1 = bresenheimLine(new MyPoint2D(0,1), new MyPoint2D(5,5));
		List<MyPoint2D> line2 = bresenheimLine(new MyPoint2D(5,0), new MyPoint2D(0,5));
		
		MyPoint2D intersect = intersectionBresenheimLine(line1, line2);
		System.out.println("The bresenheim lines intersect in (" + intersect.getX() +", " + intersect.getY() +")");
		
		System.out.println("Bye World!");
	}
}
