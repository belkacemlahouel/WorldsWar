package sim.agent.antagent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import env2.api.InterfaceMother;
import env2.body.antbody.AntMotherBody;
import env2.influences.CreateBabyInfluence;
import env2.influences.MotionInfluence;
import env2.type.WorldObjectType;

public final class AntMotherAgent extends AntAgent {

	// private static final float NB_BABIES = 0.2f; // Per second
	
	/***/
	
	private HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers;
	
	public AntMotherAgent(AntMotherBody b, int ID, HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers) {
		super(b, ID);
		this.mothers = mothers;
		this.mothers.put((InterfaceMother) getBody(), new LinkedList<CreateBabyInfluence>());
		
		// testing
		WorldObjectType type = WorldObjectType.ANTGATHERERBODY;
		addMyBaby(new CreateBabyInfluence((InterfaceMother) getBody(), type));
	}

	public MotionInfluence live() {
		/*
		 * Here I add new mothers to mothers (variable), eventually
		 * And I add all the influences I create influences to my list.
		 */
		
		// Testing but not finished yet. TODO
				
		WorldObjectType type = WorldObjectType.ANTGATHERERBODY;
		addMyBaby(new CreateBabyInfluence((InterfaceMother) getBody(), type));

		return null;
	}
	
	private void addMyBaby(CreateBabyInfluence baby) {
		/* if (mothers.get((InterfaceMother) getBody()) == null)
			mothers.put((InterfaceMother) getBody(), new LinkedList<CreateBabyInfluence>()); */
		
		mothers.get((InterfaceMother) getBody()).add(baby);
	}
	
	/*
	 * To be used when creating new mothers (in exclusivity!!).
	 */
	private int genereNextTribeID() {
		return mothers.size()+1;
	}
}
