package parser;


import parser.EnvironmentParser;
import env2.type.Direction;
import env2.body.antbody.AntNurseBody;
import env2.env.Ground;
import gui.AgentBodyGUI;
import gui.GUI;
import math.MyPoint2D;
import env2.api.AbstractEnvironment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

class Main {
	
	public static void main(String [ ] args) throws IOException {
		EnvironmentParser datas = new EnvironmentParser("src/res/conf/environment.txt");
		
		System.out.println("Hello !");
		
		datas.parseEnvDatas();
		datas.print();
		
		AbstractEnvironment env = new Ground(datas.getEnvsHeights().get(0), datas.getEnvsWidths().get(0));
		//Ground env = new Ground(10, 10);
		LinkedList<AbstractEnvironment> grounds = new LinkedList<>();
		grounds.add(env);
		GUI gui = new GUI(grounds);
		
		//Loop that initialize one body per category in the file config
		MyPoint2D newPos;
		float time;
		List<AntNurseBody> envBodies = new ArrayList<AntNurseBody>();
		AntNurseBody tmpBody;
		AgentBodyGUI tmpBodyGui; // TODO Why is this not used?
		
		List<BodyInfo> bodyStart = datas.getBodies();
		for (BodyInfo body : bodyStart) {
			newPos = new MyPoint2D(body.pos[0],body.pos[1]);
			time = new Date().getTime();
			
			//TODO find the right env to put the body in and get the quantity to initialize a quantity.
			tmpBody = new AntNurseBody((int) time, 0, env, Direction.NORTH, newPos);
			//tmpBodyGui = new AgentBodyGUI(body.type.toString(),gui,tmpBody);
			envBodies.add(tmpBody);
		}
		
	}
}