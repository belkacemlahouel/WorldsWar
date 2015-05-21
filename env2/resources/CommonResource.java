package env2.resources;

import env2.api.AbstractResource;

public abstract class CommonResource extends AbstractResource {

	private int _quantity;
	
	protected CommonResource(int quantity) {
		_quantity = quantity;
	}
	
	@Override
	public int getQuantity() {
		return _quantity;
	}
	
	@Override
	public void add(int quantity) {
		_quantity += quantity;
	}

	@Override
	public boolean isPerceivable() {
		return true;
	}

}
