package parser;

import parser.EnvironmentParser;
import env2.type.Direction;
import env2.body.TestBody;
import env2.env.Environment;
import env2.env.Ground;
import gui.AgentBodyGUI;
import gui.GUI;
import math.MyPoint2D;
import env2.api.AbstractEnvironment;

import java.io.IOException;
import java.util.Date;


class Main {
	
	public static void main(String [ ] args) throws IOException
	{
		EnvironmentParser datas = new EnvironmentParser("src/res/conf/environment.txt");
		
		System.out.println("Hello !");
		
		datas.parseEnvDatas();
		datas.print();
		
		Ground env = new Ground(datas.getEnvsHeight().get(0), datas.getEnvsWidth().get(0));
		//Ground env = new Ground(10, 10);
		GUI gui = new GUI(env);
		
		float time = new Date().getTime();
		MyPoint2D newPos = new MyPoint2D(1,1);
		TestBody antTest = new TestBody(env,Direction.NORTH, newPos, time);
		AgentBodyGUI antTestGui = new AgentBodyGUI("Ant",gui,antTest);
		
	}
}