package env;
/* 
 * $Id$
 * 
 * Copyright (c) 2008-13 Stephane GALLAND.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */


import java.util.Random;

/**
 * Define the directions in the PacMan game.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public enum Direction {
	/** North. */
	NORTH(0,-1) {
		@Override
		public Direction opposite() { return SOUTH; }
	},
	/** West. */
	WEST(-1,0) {
		@Override
		public Direction opposite() { return EAST; }
	},
	/** South. */
	SOUTH(0,+1) {
		@Override
		public Direction opposite() { return NORTH; }
	},
	/** East. */
	EAST(+1,0) {
		@Override
		public Direction opposite() { return WEST; }
	};
	
	/** Relative coordinate of the direction.
	 */
	public final int dx;
	
	/** Relative coordinate of the direction.
	 */
	public final int dy;
	
	/**
	 * @param x
	 * @param y
	 */
	Direction(int x, int y) {
		this.dx = x;
		this.dy = y;
	}

	/** Replies a random direction.
	 * 
	 * @return a random direction.
	 */
	public static Direction random() {
		Random rnd = new Random();
		return values()[rnd.nextInt(values().length)];
	}
	
	/** Replies the opposite direction.
	 * 
	 * @return the opposite direction.
	 */
	public abstract Direction opposite();
	
}
