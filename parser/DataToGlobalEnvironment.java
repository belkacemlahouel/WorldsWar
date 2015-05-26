package parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import math.MyPoint2D;
import sim.agent.AbstractAgent;
import env2.api.AbstractResource;
import env2.env.GlobalEnvironment;
import env2.env.GroundSquared;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.instanciator.bodies.ant.AntGathererInstanciator;
import env2.instanciator.bodies.ant.AntMotherInstanciator;
import env2.instanciator.bodies.ant.AntNurseInstanciator;
import env2.instanciator.bodies.ant.AntSoldierInstanciator;
import env2.instanciator.bodies.ant.AntUndertakerInstanciator;
import env2.instanciator.bodies.spider.SpiderInstanciator;
import env2.instanciator.bodies.termite.TermiteGathererInstanciator;
import env2.instanciator.bodies.termite.TermiteMotherInstanciator;
import env2.instanciator.bodies.termite.TermiteNurseInstanciator;
import env2.instanciator.bodies.termite.TermiteSoldierInstanciator;
import env2.instanciator.bodies.termite.TermiteUndertakerInstanciator;
import env2.instanciator.resources.AbstractResourceInstanciator;
import env2.instanciator.resources.FruitInstanciator;
import env2.instanciator.resources.GasInstanciator;
import env2.instanciator.resources.LeafInstanciator;
import env2.instanciator.resources.MeatInstanciator;
import env2.instanciator.resources.PoisonInstanciator;
import env2.instanciator.resources.RockInstanciator;
import env2.instanciator.resources.SugarInstanciator;
import env2.instanciator.resources.WoodInstanciator;
import env2.type.WorldObjectType;

public class DataToGlobalEnvironment {
	
	private final GlobalEnvironment GLOBAL;
	private LinkedList<AbstractAgent> AGENTS;
	
	private static final HashMap<WorldObjectType, AbstractResourceInstanciator> RESOURCE_INSTANCIATOR;
	private static final HashMap<WorldObjectType, AbstractBodyInstanciator> BODY_INSTANCIATOR;
	
	static {
		RESOURCE_INSTANCIATOR = new HashMap<>();
		RESOURCE_INSTANCIATOR.put(WorldObjectType.WOOD, new WoodInstanciator());
		RESOURCE_INSTANCIATOR.put(WorldObjectType.ROCK, new RockInstanciator());
		RESOURCE_INSTANCIATOR.put(WorldObjectType.FRUIT, new FruitInstanciator());
		RESOURCE_INSTANCIATOR.put(WorldObjectType.SUGAR, new SugarInstanciator());
		RESOURCE_INSTANCIATOR.put(WorldObjectType.GAS, new GasInstanciator());
		RESOURCE_INSTANCIATOR.put(WorldObjectType.POISON, new PoisonInstanciator());
		RESOURCE_INSTANCIATOR.put(WorldObjectType.MEAT, new MeatInstanciator());
		RESOURCE_INSTANCIATOR.put(WorldObjectType.LEAF, new LeafInstanciator());
	}
	
	static {
		BODY_INSTANCIATOR = new HashMap<>();
		BODY_INSTANCIATOR.put(WorldObjectType.ANTGATHERERBODY, new AntGathererInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTMOTHERBODY, new AntMotherInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTNURSEBODY, new AntNurseInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTSOLDIERBODY, new AntSoldierInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTUNDERTAKERBODY, new AntUndertakerInstanciator());
		
		BODY_INSTANCIATOR.put(WorldObjectType.SPIDERBODY, new SpiderInstanciator());
		
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITEGATHERERBODY, new TermiteGathererInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITEMOTHERBODY, new TermiteMotherInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITENURSEBODY, new TermiteNurseInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITESOLDIERBODY, new TermiteSoldierInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITEUNDERTAKERBODY, new TermiteUndertakerInstanciator());
	}
	
	/***/
	
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
			AbstractResource res = RESOURCE_INSTANCIATOR.get(info.getType()).getNew();
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
				BODY_INSTANCIATOR.get(info.function).getNew();
				AbstractAgent agt = BODY_INSTANCIATOR.get(info.function).getAgent();
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
