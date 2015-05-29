package env2.action.influences;

import java.util.LinkedList;

import sim.agent.AbstractAgent;
import env2.api.AbstractInfluence;
import env2.api.InterfaceMother;
import env2.type.InfluenceType;
import env2.type.WorldObjectType;

public class CreateBabyInfluence extends AbstractInfluence {

	public InterfaceMother mother;
	public LinkedList<AbstractAgent> newAgents;
	public int qty;
	public WorldObjectType type;
		
	public CreateBabyInfluence(InterfaceMother mother, LinkedList<AbstractAgent> newAgents, WorldObjectType type, int qty) {
		this.mother = mother;
		this.newAgents = newAgents;
		this.type = type;
		this.qty = qty;
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
