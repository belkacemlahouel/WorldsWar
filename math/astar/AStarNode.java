package math.astar;

import java.util.List;

import math.api.Position;

public class AStarNode {
	
	private float F, G, H;
	private Position pos;
	private AStarNode parent;
	private List<AStarNode> neighbours;
	
	public AStarNode(AStarNode parent, float g, float h) {
		G = g;
		H = h;
		F = G+H;
		this.parent = parent;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	/**
	 * @return heuristical cost
	 */
	public float hCost() {
		return H;
	}
	
	/**
	 * @return cost-so-far
	 */
	public float gCost() {
		return G;
	}
	
	/**
	 * @return total cost
	 */
	public float fCost() {
		return F;
	}
}
