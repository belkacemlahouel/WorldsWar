package test;

import math.MyPoint2D;
import env2.body.ClassicBody;
import env2.body.TestBody;
import env2.env.Environment;
import env2.env.Ground;
import env2.pheromones.Pheromones;
import env2.type.Direction;

public class main {

	public static void main(String[] args) {
		
		Environment env = new Environment();
		env.add(new TestBody(env.getEnvironment(0), Direction.SOUTH, new MyPoint2D(0, 0), 0));
		boolean b = env.add(new Pheromones(5,new MyPoint2D(0, 0), env.getEnvironment(0)));
		System.out.println(b);
		env.go();
	}
}
