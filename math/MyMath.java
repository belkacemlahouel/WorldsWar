package math;


import java.util.ArrayList;
import java.util.List;

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

	public static List<MyPoint2D> bresenheimLine(MyPoint2D start, MyPoint2D end)
	{
		return bresenheimLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
	public static List<MyPoint2D> bresenheimLine(int startX, int startY, int endX, int endY)
	{		
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
	    for (int i=0; i<=longest; i++) 
	    {
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
}
