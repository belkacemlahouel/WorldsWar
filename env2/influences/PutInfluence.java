package env2.influences;

import env2.api.AbstractInfluence;
import env2.api.AbstractResource;
import env2.api.InterfaceGatherer;
import env2.type.InfluenceType;

public class PutInfluence extends AbstractInfluence {

	public InterfaceGatherer actor;
	public AbstractResource res;
	public int qty;
	
	public PutInfluence(InterfaceGatherer actor, AbstractResource res, int qty) {
		this.actor = actor;
		this.res = res;
		this.qty = qty;
	}
	
	@Override
	public InfluenceType getType() {
		return InfluenceType.PUT;
	}
}
