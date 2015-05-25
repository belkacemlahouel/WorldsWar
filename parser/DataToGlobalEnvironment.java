package parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import env2.api.AbstractResource;
import env2.env.GlobalEnvironment;
import env2.env.GroundSquared;
import env2.instanciator.bodies.AbstractBodyInstanciator;
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
	}
	
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
			for (ResourceInfo info : DATAS.getResources2().get(key)) {
				AbstractResource res = RESOURCE_INSTANCIATOR.get(key).getNew();
				res.add(info.quantity);
				grounds.get(info.env).getCell(info.posX, info.posY).addObject(res);
			}
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
