package parser;


import java.io.IOException;

import math.MyPoint2D;
import env2.body.antbody.AntGathererBody;
import env2.body.antbody.AntSoldierBody;
import env2.type.Direction;
import gui.GUI;



class Main {
	
	public static void main(String [ ] args) throws IOException {
		System.out.println("enter");
		DataToGlobalEnvironment parser = new DataToGlobalEnvironment("src/res/conf/environment.txt");
		System.out.println("exit");

		AntGathererBody ant = new AntGathererBody(0, 1, GUI.getInstance().getEnvList().get(1), Direction.EAST, new MyPoint2D(1,5));		
		AntSoldierBody antSoldier = new AntSoldierBody(0, 1, GUI.getInstance().getEnvList().get(1), Direction.NORTH, new MyPoint2D(5,3));
		
		while(true)
		{
			GUI.getInstance().refresh(true);
		}
	}
}
