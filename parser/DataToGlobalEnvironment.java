package parser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import env2.env.GlobalEnvironment;
import env2.env.GroundSquared;
import env2.type.WorldObjectType;

public class DataToGlobalEnvironment {
	
	private final GlobalEnvironment GLOBAL;
	
	public DataToGlobalEnvironment(String filename) throws IOException {
		
		final EnvironmentParser DATAS = new EnvironmentParser(filename);
		DATAS.parseEnvDatas();
		
		final int NB_GROUNDS = DATAS.getNbGrounds();
		
		List<GroundSquared> grounds = new LinkedList<>();
		for (int i = 0; i < NB_GROUNDS; ++i) {
			grounds.add(new GroundSquared(DATAS.getEnvsWidths().get(i), DATAS.getEnvsHeights().get(i)));
		}
		
		for (PortalInfo info : DATAS.getPortalInfos()) {
			grounds.get(info.env1).addPortal(info.posEnv1[0], info.posEnv1[1],
					grounds.get(info.env2), info.posEnv2[0], info.posEnv2[1]);
		}
		
		for (WorldObjectType key : DATAS.getResources2().keySet()) {
			// TODO Check instanciator...
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
