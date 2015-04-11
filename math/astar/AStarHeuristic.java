package math.astar;

public interface AStarHeuristic {
	
	public float heuristic(int startX, int startY, int endX, int endY);
}
