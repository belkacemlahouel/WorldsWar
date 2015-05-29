package parser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import math.MyPoint2D;
import sim.agent.AbstractAgent;
import env2.api.AbstractResource;
import env2.env.GlobalEnvironment;
import env2.env.GroundSquared;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.instanciator.factory.BodyFactory;
import env2.instanciator.factory.ResourceFactory;

public class DataToGlobalEnvironment {
	
	private final GlobalEnvironment GLOBAL;
	private LinkedList<AbstractAgent> AGENTS;
	
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
		
		for (ResourceInfo info : DATAS.getResourcesInfos()) {
			AbstractResource res = ResourceFactory.RESOURCE_INSTANCIATOR.get(info.getType()).getNew();
			res.add(info.quantity);
			grounds.get(info.env).getCell(info.posX, info.posY).addObject(res);
		}
		
		/*
		 * Instanciation of bodies and agents + connections
		 * Instanciation of GUIBodies should be done in GUI constructor... given a set of bodies
		 */
		
		AGENTS = new LinkedList<>();
		for (BodyInfo info : DATAS.getBodiesInfos()) {
			AbstractBodyInstanciator.POS = new MyPoint2D(info.pos[0], info.pos[1]);
			AbstractBodyInstanciator.ENV = grounds.get(info.env);
			AbstractBodyInstanciator.TRIBE_ID = info.tribeId;
						
			for (int i = 0; i < info.quantity; ++i) {
				BodyFactory.BODY_INSTANCIATOR.get(info.function).getNew();
				AbstractAgent agt = BodyFactory.BODY_INSTANCIATOR.get(info.function).getAgent();
				AGENTS.add(agt);
			}
		}
		
		GLOBAL = new GlobalEnvironment(grounds);
	}
	
	public GlobalEnvironment getGlobalEnvironment() {
		return GLOBAL;
	}
	
	public LinkedList<AbstractAgent> getAgents() {
		return AGENTS;
	}
}
