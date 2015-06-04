package parser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import math.MyPoint2D;
import parser.DataToGlobalEnvironment;
import gui.*;
import env2.api.AbstractEnvironment;
import env2.env.Ground;
import env2.resources.Wood;
import env2.type.Direction;
import env2.body.antbody.*;



class Main {
	
	public static void main(String [ ] args) throws IOException {
		DataToGlobalEnvironment parser = new DataToGlobalEnvironment("src/res/conf/environment.txt");
		
		/*Ground env = new Ground(10, 10);
		List<AbstractEnvironment> listEnvs = new ArrayList<AbstractEnvironment>();
		listEnvs.add(env);
		
		GUI gui = GUI.getInstance();
		gui.setEnvironmentList(listEnvs);*/

		AntGathererBody ant = new AntGathererBody(0, 1, GUI.getInstance().getEnvList().get(1), Direction.EAST, new MyPoint2D(1,5));		
		AntSoldierBody antSoldier = new AntSoldierBody(0, 1, GUI.getInstance().getEnvList().get(1), Direction.NORTH, new MyPoint2D(5,3));
		
		while(true)
		{
			GUI.getInstance().refresh();
		}
	}
}
