package env2.influences;

import java.util.List;

import env2.api.AbstractInfluence;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceGatherer;
import env2.type.InfluenceType;

public class PickInfluence extends AbstractInfluence {

	public InterfaceGatherer picker;
	public AbstractResource picked;
	public int qty;
	public List<AbstractWorldObject> container;
	
	public PickInfluence(InterfaceGatherer body, List<AbstractWorldObject> list, AbstractResource resource, int qty) {
		picker = body;
		picked = resource;
		this.qty = qty;
		this.container = list;
	}

	@Override
	public InfluenceType getType() {
		return InfluenceType.PICK;
	}
}
