package sim.agent.antagent;

import env2.body.antbody.AntUndertakerBody;
import env2.influences.MotionInfluence;
import env2.type.Time;

public final class AntUndertakerAgent extends AntAgent {
	
	public AntUndertakerAgent(AntUndertakerBody b, int ID) {
		super(b, ID);
		// TODO Auto-generated constructor stub
	}

	public MotionInfluence live() {
		// TODO
		if (body.isDead()) {
			System.out.println("I'm dead: " + body.getPosition());
			return null;
		}
		
		if (body.isBaby(Time.getTime())) {
			return null;
		}
		
		//return wander();
		return null;
	}
}
