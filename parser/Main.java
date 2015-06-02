package parser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import math.MyPoint2D;
import parser.DataToGlobalEnvironment;
import gui.*;
import env2.api.AbstractEnvironment;
import env2.env.Ground;
import env2.type.Direction;
import env2.body.antbody.*;



class Main {
	
	public static void main(String [ ] args) throws IOException {
		DataToGlobalEnvironment parser = new DataToGlobalEnvironment("src/res/conf/environment.txt");
		
		Ground env = new Ground(10, 10);
		List<AbstractEnvironment> listEnvs = new ArrayList<AbstractEnvironment>();
		listEnvs.add(env);
		GUI gui = new GUI(listEnvs);
		
		AntGathererBody ant = new AntGathererBody(0, 1, env, Direction.EAST, new MyPoint2D(1,5));
		AgentBodyGUI agentGui = new AgentBodyGUI("Ant", gui, ant);
		agentGui.move();
		
		AntSoldierBody antSoldier = new AntSoldierBody(0, 1, env, Direction.NORTH, new MyPoint2D(5,3));
		AgentBodyGUI agentGuiSoldier = new AgentBodyGUI("Ant", gui, antSoldier);
		agentGui.move();
	}
}