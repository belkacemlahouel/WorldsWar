package sim.agent.termiteagent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import env2.api.InterfaceMother;
import env2.body.termitebody.TermiteMotherBody;
import env2.influences.CreateBabyInfluence;
import env2.influences.MotionInfluence;

public final class TermiteMotherAgent extends TermiteAgent {
	
	private HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers;

	public TermiteMotherAgent(TermiteMotherBody b, int ID, HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers) {
		super(b, ID);
		this.mothers = mothers;
		mothers.put((InterfaceMother) getBody(), new LinkedList<CreateBabyInfluence>());
		// TODO Auto-generated constructor stub
	}

	public MotionInfluence live() {
		/*
		 * Here I add new mothers to mothers
		 * And I add all the influences I create influences to my list.
		 */
		// mothers.get(getBody()).add(new CreateBabyInfluence(null, null, null, TRIBE_ID));
		// TODO This represents a kind-of model
		return null;
	}
	
}
