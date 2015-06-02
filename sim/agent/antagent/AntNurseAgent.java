package sim.agent.antagent;

import java.util.Iterator;

import env2.api.AbstractBody;
import env2.api.AbstractWorldObject;
import env2.body.antbody.AntNurseBody;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.frustrum.old.AbstractFrustrum;
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
		
		if(!body.isBaby(Time.getTime())){
			AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
			Iterator<AbstractWorldObject> objs = frustrum.objects();
			/* The mission of the nurse is to heal injured ants and to feed babies. */
			boolean mission = false;
			AbstractWorldObject goal = null;
			
			while(objs.hasNext() && mission==false){
				AbstractWorldObject obj = objs.next();
				
				if(WorldObjectType.isAntBody(obj.getType()) || WorldObjectType.isTermiteBody(obj.getType())){
					AbstractBody objBody = (AbstractBody) obj;
					if(objBody.isFriend(body) && this.isHurt(objBody)){
						mission = true;
						heal(objBody);
					}else if (objBody.isBaby(Time.getTime()) && target==null){
						target = objBody;
					}
				}
			}
			
			if(!mission && target!=null){
				feed(target);
			}
		}
		
		return null;
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
	private void heal(AbstractBody body){
		boolean goodPosition = this.isOnSamePosition(body);
		
		if(goodPosition){
			//do action on goal
		}else{
			//move to goal
		}
	}
	
	/**
	 * Feed a baby.
	 * @param baby, the baby to feed.
	 */
	private void feed(AbstractBody baby){
		//TODO : take food and move to baby to feed it.
	}
	
}
