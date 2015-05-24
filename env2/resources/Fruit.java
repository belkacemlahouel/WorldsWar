package env2.resources;

import env2.type.WorldObjectType;

public final class Fruit extends CommonResource {

	public Fruit(int quantity) {
		super(quantity);
	}

	@Override
	public Fruit pick(int quantity) {
		return new Fruit(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.FRUIT;
	}

}
