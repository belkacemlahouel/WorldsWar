package env2.resources;

import env2.type.WorldObjectType;

public final class Gas extends CommonResource {

	public Gas(int quantity) {
		super(quantity);
	}

	@Override
	public Gas pick(int quantity) {
		return new Gas(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.GAS;
	}

}
