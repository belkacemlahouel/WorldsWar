package parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import env2.api.AbstractResource;
import env2.env.GlobalEnvironment;
import env2.env.GroundSquared;
import env2.instanciator.AbstractResourceInstanciator;
import env2.instanciator.FruitInstanciator;
import env2.instanciator.GasInstanciator;
import env2.instanciator.LeafInstanciator;
import env2.instanciator.MeatInstanciator;
import env2.instanciator.PoisonInstanciator;
import env2.instanciator.RockInstanciator;
import env2.instanciator.SugarInstanciator;
import env2.instanciator.WoodInstanciator;
import env2.type.WorldObjectType;

public class DataToGlobalEnvironment {
	
	private final GlobalEnvironment GLOBAL;
	private static final HashMap<WorldObjectType, AbstractResourceInstanciator> instanciators;
	
	static {
		instanciators = new HashMap<>();
		instanciators.put(WorldObjectType.WOOD, new WoodInstanciator());
		instanciators.put(WorldObjectType.ROCK, new RockInstanciator());
		instanciators.put(WorldObjectType.FRUIT, new FruitInstanciator());
		instanciators.put(WorldObjectType.SUGAR, new SugarInstanciator());
		instanciators.put(WorldObjectType.GAS, new GasInstanciator());
		instanciators.put(WorldObjectType.POISON, new PoisonInstanciator());
		instanciators.put(WorldObjectType.MEAT, new MeatInstanciator());
		instanciators.put(WorldObjectType.LEAF, new LeafInstanciator());
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
				AbstractResource res = instanciators.get(key).getNew();
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
