package math.astar;

import java.util.List;

import math.api.Position;

public interface GenericMapNode {
	
	/**
	 * @return if this node is walkable or not
	 */
	public boolean isObstacle();
	
	/**
	 * @return neighbours of this node
	 */
	public List<GenericMapNode> getNeighbours();
	
	/**
	 * @return position of this node (coordinates)
	 */
	public Position getPosition();
	
	/**
	 * @return cell at (x, y)
	 */
	public GenericMapNode getAt(int x, int y);
}
