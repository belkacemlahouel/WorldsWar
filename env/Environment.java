package env;

import math.MyPoint2D;

public class Environment {
	
	public static void main(String[] args) {
		
		// TODO Tests
	}
	
	/***/
	
	private Cell[][] grid;
	private final int WIDTH, HEIGHT;
	
	public Environment(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		
		grid = new Cell[WIDTH][HEIGHT];
		for (int i = 0; i < WIDTH; ++i) {
			for (int j = 0; j < HEIGHT; ++j) {
				grid[i][j] = new Cell();
			}
		}
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public Cell getCell(int x, int y) {
		return grid[x][y];
	}
	
	public void move(Movable o, Direction dir) {
		if (!movePossible(o, dir))
			return;
			
		MyPoint2D cur = o.getPosition();
		WorldObject tmp = grid[(int) cur.getX()][(int) cur.getY()].getObject();
		grid[(int) cur.getX()][(int) cur.getY()].addObject(null);
		cur.setLocation(cur.getX()+dir.dx, cur.getY()+dir.dy);
		grid[(int) cur.getX()][(int) cur.getY()].addObject(tmp);
	}
	
	private boolean movePossible(Movable o, Direction dir) {
		MyPoint2D cur = o.getPosition();
		int newX, newY;
		newX = (int) cur.getX()+dir.dx;
		newY = (int) cur.getY()+dir.dy;
		return (newX >= 0 && newX < WIDTH) && (newY >= 0 && newY < HEIGHT) && getCell(newX, newY).isEmpty();
	}
	
	public void printEnvironment() {
		System.out.println("ENVIRONMENT:");
		
		for (int i = 0; i < WIDTH; ++i) {
			for (int j = 0; j < HEIGHT; ++j) {
				if (!getCell(i, j).isEmpty()) {
					WorldObject o = getCell(i, j).getObject();
					if (o != null && !o.toString().isEmpty()) {
						System.out.println("\t(" + i + ", " + j + ") " + o.toString());
					}
				}
			}
		}
	}
	
	/***/
	
}
