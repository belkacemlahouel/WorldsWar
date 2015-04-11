package env.environment;

import env.Cell;
import env.api.Movable;
import math.MyPoint2D;

public abstract class Environment {
	
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
	
	/***/
	
	public abstract void applyInfluences();
	public abstract void solveConflicts();
	public abstract void doStep();
	public abstract void move(Movable o, MyPoint2D end);
	public abstract void printEnvironment();
	protected abstract boolean movePossible(Movable o, MyPoint2D end);
}
