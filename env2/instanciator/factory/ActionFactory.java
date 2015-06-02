package env2.instanciator.factory;

import java.util.HashMap;

import env2.instanciator.actions.AbstractActionInstanciator;
import env2.instanciator.actions.AttackCureActionInstanciator;
import env2.instanciator.actions.BuryDeadActionInstanciator;
import env2.instanciator.actions.CreateBabyActionInstanciator;
import env2.instanciator.actions.DropPheromoneActionInstanciator;
import env2.instanciator.actions.EatActionInstanciator;
import env2.instanciator.actions.KillMyselfActionInstanciator;
import env2.instanciator.actions.MotionActionInstanciator;
import env2.instanciator.actions.PickActionInstanciator;
import env2.instanciator.actions.PutActionInstanciator;
import env2.instanciator.actions.RemoveMeActionInstanciator;
import env2.type.InfluenceType;

public class ActionFactory {

	public static final HashMap<InfluenceType, AbstractActionInstanciator> ACTION_INSTANCIATOR;
	
	static {
		ACTION_INSTANCIATOR = new HashMap<>();
		ACTION_INSTANCIATOR.put(InfluenceType.ATTACK_CURE, new AttackCureActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.BURY_DEAD, new BuryDeadActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.CREATE_BABY, new CreateBabyActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.DROP_PHEROMONE, new DropPheromoneActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.EAT, new EatActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.KILL_MYSELF, new KillMyselfActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.MOTION, new MotionActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.PICK, new PickActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.PUT, new PutActionInstanciator());
		ACTION_INSTANCIATOR.put(InfluenceType.REMOVE_ME, new RemoveMeActionInstanciator());
	}
}
