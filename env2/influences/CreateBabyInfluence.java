package env2.influences;

import env2.api.AbstractInfluence;
import env2.api.InterfaceMother;
import env2.type.InfluenceType;
import env2.type.WorldObjectType;

public class CreateBabyInfluence extends AbstractInfluence {

	public InterfaceMother mother;
	public WorldObjectType type;
		
	public CreateBabyInfluence(InterfaceMother mother, WorldObjectType type) {
		this.mother = mother;
		this.type = type;
	}

	@Override
	public InfluenceType getType() {
		return InfluenceType.CREATE_BABY;
	}
}

/*
 * AntNurseAgent.class
 * public CreateBabyInfluence(InterfaceMother mother, LinkedList<Class<? extends AbstractAgent>> newAgents, int qty) {
 */
