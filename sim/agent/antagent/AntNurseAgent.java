package sim.agent.antagent;

import java.util.Iterator;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractWorldObject;
import env2.body.antbody.AntNurseBody;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.AbstractWorldObjectWithPosition;
import env2.influences.MotionInfluence;


/**
 * Implementation of nurse agent which aims to feed the babies and heal injured ants.
 *
 */
public final class AntNurseAgent extends AntAgent {

	public AntNurseAgent(AntNurseBody b, int ID) {
		super(b, ID);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Implementation of the basic behavior of a nurse ant.
	 */
	public MotionInfluence live() {
		AbstractBody body = this.getBody();
		AbstractBody target = null;
		MotionInfluence influence = null;
		
		if(!body.isBaby(Time.getTime())){
			AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
			Iterator<AbstractWorldObjectWithPosition> objs = frustrum.objects();
			
			/* The mission of the nurse is to heal injured ants and to feed babies. */
			boolean mission = false;
			AbstractWorldObjectWithPosition goal = null;
			
			while(objs.hasNext() && mission==false){
				AbstractWorldObject obj = objs.next().object;
				
				if(WorldObjectType.isAntBody(obj.getType()) || WorldObjectType.isTermiteBody(obj.getType())){
					AbstractBody objBody = (AbstractBody) obj;
					if(objBody.isFriend(body) && this.isHurt(objBody)){
						mission = true;
						influence = heal(objBody);
					}else if (objBody.isBaby(Time.getTime()) && target==null){
						target = objBody;
					}
				}
			}
			
			if(!mission && target!=null){
				influence = feed(target);
			}
		}
		
		return influence;
	}
	
	
	/**
	 * Tell to the nurse if an agent is injured.
	 * @param body, the body to test.
	 */
	private boolean isHurt(AbstractBody body){
		int life = body.getLife();
		int life_percent = (life/body.getMaxLife())*100;
		
		if(life_percent <= 50){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * Heal an injured agent.
	 * @param body, the body to heal.
	 */
	private MotionInfluence heal(AbstractBody body){
		boolean goodPosition = this.isOnSamePosition(body);
		MotionInfluence influence;
		
		if(goodPosition){
			//TODO : add action heal
			influence = null;
		}else{
			//move to goal
			MyPoint2D goalPos = body.getPosition();
			influence = new MotionInfluence(body, goalPos, body.getEnvironment());
		}
		
		return influence;
	}
	
	/**
	 * Feed a baby.
	 * @param baby, the baby to feed.
	 */
	private MotionInfluence feed(AbstractBody baby){
		boolean goodPosition = this.isOnSamePosition(baby);
		MotionInfluence influence;
		
		if(goodPosition){
			//TODO : add action feed
			influence = null;
		}else{
			//move to goal
			MyPoint2D goalPos = baby.getPosition();
			influence = new MotionInfluence(body, goalPos, body.getEnvironment());
		}
		
		return influence;
	}
	
}
