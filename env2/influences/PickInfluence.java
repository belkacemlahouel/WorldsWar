package env2.influences;

import env2.api.AbstractInfluence;
import env2.api.AbstractResource;
import env2.api.InterfaceGatherer;
import env2.type.InfluenceType;

public class PickInfluence extends AbstractInfluence {

	public InterfaceGatherer picker;
	public AbstractResource picked;
	public int qty;
	
	public PickInfluence(InterfaceGatherer body, AbstractResource resource, int qty) {
		picker = body;
		picked = resource;
		this.qty = qty;
	}

	@Override
	public InfluenceType getType() {
		return InfluenceType.PICK;
	}
}
