package env2.resources;

import env2.type.WorldObjectType;

public final class Sugar extends CommonResource {
	
	public Sugar(int quantity) {
		super(quantity);
	}

	@Override
	public Sugar pick(int quantity) {
		return new Sugar(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.SUGAR;
	}
	
}
