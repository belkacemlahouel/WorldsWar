package math;

import java.util.ArrayList;
import java.util.List;

import sim.Agent;
import math.api.Evaluator;
import math.api.Position;

interface GenericMapNode {
	
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

interface AStarHeuristic {
	
	public float heuristic(int startX, int startY, int endX, int endY);
}

/***/

class AStarNode {
	
	private float F, G, H, cost;
	private Position pos;
	private AStarNode parent;
	private List<AStarNode> neighbours;
	
	public AStarNode(AStarNode parent, float g, float h) {
		G = g;
		H = h;
		F = G+H;
		// Parent
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

public class AStarPath implements AStarHeuristic, Evaluator {
	
	private Position start, end;
	private Path ans;
	private List<AStarNode> open, closed;
	private Agent agt;
	
	public AStarPath(Agent agt, Position start, Position end) {
		this.start = start;
		this.end = end;
		
		open = new ArrayList<AStarNode>();
		closed = new ArrayList<AStarNode>();
		
		ans = new Path();
		
		// open.add(new AStarNode());
		
		computePath();
	}
	
	private void computePath() {
		while (end.getX() != start.getX() || end.getY() != start.getY()) {
			AStarNode best = open.remove(0);
			
			closed.add(best);
		}
	}
	
	/***/
	
	public int interest(Position pos) {
		return 1;
	}

	public float heuristic(int startX, int startY, int endX, int endY) {
		return (float) MyMath.manhattanDistance(startX, startY, endX, endY);
	}
}
