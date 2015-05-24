package parser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import env2.api.AbstractEnvironment;
import env2.env.GlobalEnvironment;
import env2.env.GroundSquared;

public class DataToGlobalEnvironment {
	
	private final GlobalEnvironment GLOBAL;
	
	public DataToGlobalEnvironment(String filename) throws IOException {
		
		EnvironmentParser datas = new EnvironmentParser(filename);
		datas.parseEnvDatas();
		
		final int NB_GROUNDS = datas.getNbGrounds();
		
		List<AbstractEnvironment> grounds = new LinkedList<>();
		for (int i = 0; i < NB_GROUNDS; ++i) {
			grounds.add(new GroundSquared(datas.getEnvsWidths().get(i), datas.getEnvsHeights().get(i)));
		}
		
		/*
		 * TODO
		 * Instanciation of bodies and agents + connections
		 * Instanciation of GUIBodies should be done in GUI constructor... given a set of bodies
		 */
		
		GLOBAL = new GlobalEnvironment(grounds);
	}
	
	public GlobalEnvironment getGlobalEnvironment() {
		return GLOBAL;
	}
}
