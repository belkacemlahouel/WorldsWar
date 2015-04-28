package env2.env;

import env2.api.AbstractCell;
import env2.api.AbstractEnvironment;

public class Ground extends AbstractEnvironment {
	
	/*** attributes ***/
	
	private AbstractCell[][] grid;
	private final int WIDTH, HEIGHT;
	
	public Ground(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		
		grid = new AbstractCell[WIDTH][HEIGHT];
		for (int i = 0; i < WIDTH; ++i) {
			for (int j = 0; j < HEIGHT; ++j) {
				grid[i][j] = new Cell(); // TODO change the "new" call
			}
		}
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public AbstractCell getCell(int x, int y) {
		// return grid[x][y]; // TODO check that
		return null;
	}
	
	/*** additionnal methods ***/
}
