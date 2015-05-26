package env2.type;

import java.util.HashMap;

public enum WorldObjectType {
	// BODIES
	ANTBODY,
		ANTGATHERERBODY,
		ANTMOTHERBODY,
		ANTNURSEBODY,
		ANTSOLDIERBODY,
		ANTUNDERTAKERBODY,
	SPIDERBODY,
	TERMITEBODY,
		TERMITEGATHERERBODY,
		TERMITEMOTHERBODY,
		TERMITENURSEBODY,
		TERMITESOLDIERBODY,
		TERMITEUNDERTAKERBODY,
	// RESOURCES
	RESOURCE,
		ROCK,
		WOOD,
		LEAF,
		MEAT,
		SUGAR,
		FRUIT,
		POISON,
		GAS,
	// PARTICULAR TYPES (INFORMATION THROUGH PHEROMONES...)
	PHEROMONE,
		DANGERPHEROMONE,
		FOODPHEROMONE;
	
	/*
	 * These methods are useful to test if
	 * any particular object is in one of these big categories.
	 * The chained tests are storted by probabilities of occurrences.
	 * 
	 * Can be food returns if this resource can be eaten by a given specie.
	 */
	
	public static boolean isPheromone(WorldObjectType type) {
		return (type.ordinal() >= PHEROMONE.ordinal() &&
				type.ordinal() <= FOODPHEROMONE.ordinal());
	}
	
	public static boolean canBeFood(WorldObjectType type) {
		return (type.ordinal() >= ROCK.ordinal() &&
				type.ordinal() <= GAS.ordinal());
	}
	
	public static boolean isAntBody(WorldObjectType type) {
		return (type.ordinal() >= ANTBODY.ordinal() &&
				type.ordinal() <= ANTUNDERTAKERBODY.ordinal());
	}
	
	public static boolean isTermiteBody(WorldObjectType type) {
		return (type.ordinal() >= TERMITEBODY.ordinal() &&
				type.ordinal() <= TERMITEUNDERTAKERBODY.ordinal());
	}
	
	/**
	 * Get Type with String
	 */
	
	private final static HashMap<String, WorldObjectType> TYPES;
	
	static {
		TYPES = new HashMap<>();

		TYPES.put("ANTGATHERERBODY", ANTGATHERERBODY);
		TYPES.put("ANTMOTHERBODY", ANTMOTHERBODY);
		TYPES.put("ANTNURSEBODY", ANTNURSEBODY);
		TYPES.put("ANTSOLDIERBODY", ANTSOLDIERBODY);
		TYPES.put("ANTUNDERTAKERBODY", ANTUNDERTAKERBODY);
		
		TYPES.put("SPIDERBODY", SPIDERBODY);
		
		TYPES.put("TERMITEGATHERERBODY", TERMITEGATHERERBODY);
		TYPES.put("TERMITEMOTHERBODY", TERMITEMOTHERBODY);
		TYPES.put("TERMITENURSEBODY", TERMITENURSEBODY);
		TYPES.put("TERMITESOLDIERBODY", TERMITESOLDIERBODY);
		TYPES.put("TERMITEUNDERTAKERBODY", TERMITEUNDERTAKERBODY);
		
		TYPES.put("ROCK", ROCK);
		TYPES.put("WOOD", WOOD);
		TYPES.put("LEAF", LEAF);
		TYPES.put("MEAT", MEAT);
		TYPES.put("SUGAR", SUGAR);
		TYPES.put("FRUIT", FRUIT);
		TYPES.put("POISON", POISON);
		TYPES.put("GAS", GAS);
		
		TYPES.put("DANGERPHEROMONE", DANGERPHEROMONE);
		TYPES.put("FOODPHEROMONE", FOODPHEROMONE);
	}
	
	public static WorldObjectType getType(String key) {
		return TYPES.get(key);
	}
	
	/**
	 * Test method
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("ANTBODY = " + ANTBODY);
		System.out.println("GAS = " + GAS);
		System.out.println("isAntBody(ANTGATHERERBODY) = " + isAntBody(ANTGATHERERBODY));
		System.out.println("isAntBody(TERMITEBODY) = " + isAntBody(TERMITEBODY));
		System.out.println("Bye World!");
	}
}
