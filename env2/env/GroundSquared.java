package env2.env;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;

/**
 * This class allows the environment to be connected to other environments
 * with the use of PortalCells
 * @author belka
 *
 */

public class GroundSquared extends Ground {

	public GroundSquared(int width, int height) {
		super(width, height);
	}
	
	/**
	 * Creates a new cell with a portal in ax, ay
	 * @param ax portal position in this environment
	 * @param ay
	 * @param benv arrival environment
	 * @param bx portal arrival position in the arrival environment 
	 * @param by
	 * @return did I succeed to change this cell into a new portal?
	 */
	public boolean addPortal(int ax, int ay, AbstractEnvironment benv, int bx, int by) {
		if (!grid[ax][ay].isPortal()) {
			grid[ax][ay] = new PortalCell(benv, new MyPoint2D(bx, by));
			return true;
		}
		
		return false;
	}
}
