package env2.resources;

import env2.type.WorldObjectType;

public final class Poison extends CommonResource {

	public Poison(int quantity) {
		super(quantity);
	}

	@Override
	public Poison pick(int quantity) {
		return new Poison(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.POISON;
	}
	
}
