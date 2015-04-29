package parser;

import parser.EnvironmentParser;

import java.io.IOException;

class Main {
	
	public static void main(String [ ] args) throws IOException
	{
		EnvironmentParser datas = new EnvironmentParser("src/res/conf/environment.txt");
		
		System.out.println("Hello !");
		
		datas.parseEnvDatas();
		
		System.out.println(datas);
	}
}