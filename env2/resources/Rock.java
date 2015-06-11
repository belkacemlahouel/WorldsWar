package env2.resources;

import env2.type.WorldObjectType;

public final class Rock extends CommonResource {

	public Rock(int quantity) {
		super(quantity);
	}

	@Override
	public Rock pick(int quantity) {
		this.add(-quantity);
		return new Rock(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.ROCK;
	}
	
}
