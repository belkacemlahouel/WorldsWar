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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
		
		/*float time = new Date().getTime();
		MyPoint2D newPos = new MyPoint2D(1,1);
		TestBody antTest = new TestBody(env,Direction.NORTH, newPos, time);
		AgentBodyGUI antTestGui = new AgentBodyGUI("Ant",gui,antTest);*/
		
		MyPoint2D newPos;
		float time;
		List<TestBody> envBodies = new ArrayList<TestBody>();
		TestBody tmpBody;
		AgentBodyGUI tmpBodyGui;
		
		List<BodyInfo> bodyStart = datas.getBodies();
		for (BodyInfo body : bodyStart)
		{
			newPos = new MyPoint2D(body.pos[0],body.pos[1]);
			time = new Date().getTime();
			
			//TODO find the right env to put the body in.
			tmpBody = new TestBody(env,Direction.NORTH, newPos, time);
			tmpBodyGui = new AgentBodyGUI(body.type.toString(),gui,tmpBody);
			envBodies.add(tmpBody);
		}
		
	}
}