package math;

import java.util.ArrayList;
import java.util.List;

import math.api.Position;

public class Path {

	private List<Position> points;
	
	public Path() {
		points = new ArrayList<Position>();
	}
	
	public int getLength() {
	    return points.size();
	}
	
	public int getX(int index) {
	        return points.get(index).getX();
	}
	
	public int getY(int index) {
	        return points.get(index).getY();
	}
	
	public void append(Position p) {
	        points.add(p);
	}
	
	public void prepend(Position p) {
	        points.add(0, p);
	}
	
	public boolean contains(int x, int y) {
		for (Position tmp : points) {
			if (tmp.getX() == x && y == tmp.getY()) {
				return true;
			}
		}
		
		return false;
	}
}
