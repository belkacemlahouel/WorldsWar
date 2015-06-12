package sim.agent.antagent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import math.MyMath;
import env2.api.InterfaceMother;
import env2.body.antbody.AntMotherBody;
import env2.influences.CreateBabyInfluence;
import env2.influences.MotionInfluence;
import env2.type.Time;
import env2.type.WorldObjectType;

public final class AntMotherAgent extends AntAgent {
	
	/* 
	 * Warning : this part is frame (update) dependent
	 * and not time-dependent.
	 */

	private static final int MIN_BABIES = 1;	// inclusive with MyMath.Random
	private static final int MAX_BABIES = 6;	// exclusive with MyMath.Random
	
	/***/
	
	private HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers; // static? final?
	
	public AntMotherAgent(AntMotherBody b, int ID, HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers) {
		super(b, ID);
		this.mothers = mothers;
	}

	public MotionInfluence live() {
		/*
		 * Here I add new mothers to mothers (variable), eventually
		 * And I add all the influences I create influences to my list.
		 */
		
		int nb_babies = MyMath.random(MIN_BABIES, MAX_BABIES);
		
		if(body.isBaby(Time.getTime())){
			return null;
		}
		
		for (int i = 0; i < nb_babies; ++i) {
			int proba = MyMath.random(1, 101);
			
			/*
			 * probabilities order from the most probable to the least
			 */
			WorldObjectType type;
			if (proba < 45)
				type = WorldObjectType.ANTGATHERERBODY;
			else if (proba < 75)
				type = WorldObjectType.ANTSOLDIERBODY;
			else if (proba < 95)
				type = WorldObjectType.ANTNURSEBODY;
			else if (proba < 100)
				type = WorldObjectType.ANTUNDERTAKERBODY;
			else
				type = WorldObjectType.ANTMOTHERBODY;
			
			addMyBaby(new CreateBabyInfluence((InterfaceMother) getBody(), type));
		}
		
		// Testing but not finished yet. TODO	
		
		return null;
	}
	
	private void addMyBaby(CreateBabyInfluence baby) {
		if (mothers.get((InterfaceMother) getBody()) == null)
			mothers.put((InterfaceMother) getBody(), new LinkedList<CreateBabyInfluence>());
		
		mothers.get((InterfaceMother) getBody()).add(baby);
	}
}
