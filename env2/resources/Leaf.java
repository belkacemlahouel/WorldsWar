package env2.resources;

import env2.type.WorldObjectType;

public final class Leaf extends CommonResource {

	public Leaf(int quantity) {
		super(quantity);
	}

	@Override
	public Leaf pick(int quantity) {
		this.add(-quantity);
		return new Leaf(quantity);
	}

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.LEAF;
	}
}
