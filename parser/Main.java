package parser;

import java.io.IOException;

import env2.api.AbstractEnvironment;
import env2.env.Ground;
import gui.GUI;

class Main {
	
	public static void main(String [ ] args) throws IOException
	{
		EnvironmentParser datas = new EnvironmentParser("src/res/conf/environment.txt");
		
		System.out.println("Hello !");
		
		datas.parseEnvDatas();
		datas.print();
		
		AbstractEnvironment env = new Ground(datas.getEnvsHeight().get(0), datas.getEnvsWidth().get(0));
		//Ground env = new Ground(10, 10);
		GUI gui = new GUI(env);
		
		/*AntBody antTest = new AntBody(env,Direction.NORTH);
		AgentBodyGUI antTestGui = new AgentBodyGUI("Ant",gui,antTest);*/
		
	}
}