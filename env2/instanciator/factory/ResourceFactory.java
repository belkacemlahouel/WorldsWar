package env2.instanciator.factory;

import java.util.HashMap;

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

public class ResourceFactory {

	public static final HashMap<WorldObjectType, AbstractResourceInstanciator> RESOURCE_INSTANCIATOR;

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
}
