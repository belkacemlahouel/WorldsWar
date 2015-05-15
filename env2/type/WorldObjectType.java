package env2.type;

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
	ROCK,
	WOOD,
	LEAF,
	MEAT,
	SUGAR,
	FRUIT,
	POISON,
	GAS;
	
	/*
	 * These methods are useful to test if
	 * any particular object is in one of these big categories.
	 * The chained tests are storted by probabilities of occurrences.
	 * 
	 * Can be food returns if this resource can be eaten by a given specie.
	 */
	
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
