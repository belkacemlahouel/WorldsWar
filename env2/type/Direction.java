package env2.type;

import java.util.Random;

public enum Direction {
	
	NORTH(0,+1) {
		public Direction opposite() { return SOUTH; }
	},
	
	WEST(-1,0) {
		public Direction opposite() { return EAST; }
	},
	
	SOUTH(0,-1) {
		public Direction opposite() { return NORTH; }
	},
	
	EAST(+1,0) {
		public Direction opposite() { return WEST; }
	};
	
	/***/
	
	public final int dx, dy;
	
	Direction(int x, int y) {
		this.dx = x;
		this.dy = y;
	}

	public static Direction random() {
		Random rnd = new Random();
		return values()[rnd.nextInt(values().length)];
	}

	public abstract Direction opposite();
}
