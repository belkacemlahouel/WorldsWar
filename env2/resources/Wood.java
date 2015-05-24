package env2.resources;

import env2.type.WorldObjectType;

public final class Wood extends CommonResource {
	
	public Wood(int quantity) {
		super(quantity);
	}

	@Override
	public Wood pick(int quantity) {
		return new Wood(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.WOOD;
	}

}
