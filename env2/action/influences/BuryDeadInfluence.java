package env2.action.influences;

import env2.api.AbstractBody;
import env2.api.AbstractInfluence;
import env2.api.InterfaceUndertaker;
import env2.type.InfluenceType;

public class BuryDeadInfluence extends AbstractInfluence {

	public InterfaceUndertaker undertaker;
	public AbstractBody deadbody;
	
	public BuryDeadInfluence(InterfaceUndertaker undertaker, AbstractBody deadbody) {
		this.undertaker = undertaker;
		this.deadbody = deadbody;
	}
	
	@Override
	public InfluenceType getType() {
		return InfluenceType.BURY_DEAD;
	}

}
