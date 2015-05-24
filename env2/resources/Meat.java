package env2.resources;

import env2.type.WorldObjectType;

public final class Meat extends CommonResource {
	
	public Meat(int quantity) {
		super(quantity);
	}

	@Override
	public Meat pick(int quantity) {
		return new Meat(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.MEAT;
	}
	
}
