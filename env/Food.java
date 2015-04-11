package env;

public abstract class Food extends WorldObject {

	/**
	 * @return quantity available of this Food
	 */
	protected abstract int quantity();
	
	/**
	 * @return time to collect one unit of this Food
	 */
	protected abstract int collectTime();
	
	/**
	 * @return characteristics of this food: cure, power, etc
	 */
	protected abstract FoodType type();
}

enum FoodType {
	CURE,
	POWER;
	// TODO Fill this list up
}

interface Perishable {
	// TODO Add a count down method?
}

interface Obstacle {
	// TODO
}

/*
 * Wall, Water, Tree, Rocks, AntFood, Meat, TermiteFood, etc
 */
