package math.astar;

import java.util.ArrayList;
import java.util.List;

import sim.agent.AbstractAgent;
import math.MyMath;
import math.Path;
import math.api.Evaluator;
import math.api.Position;

public class AStarPath implements AStarHeuristic, Evaluator {
	
	private Position start, end;
	private Path ans;
	private List<AStarNode> open, closed;
	private AbstractAgent agt;
	
	public AStarPath(AbstractAgent agt, Position start, Position end) {
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
