package sim.agent.antagent;

import java.util.Iterator;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceGatherer;
import env2.api.InterfaceNurse;
import env2.body.antbody.AntNurseBody;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.env.PortalCell;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.Perception;
import env2.influences.AttackCureInfluence;
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
		
		if (body.isDead()) {
			System.out.println("I'm dead: " + body.getPosition());
			return null;
		}
		
		if(!body.isBaby(Time.getTime())){
			//test if the cell is a portal
			if(!body.getEnvironment().getCell(body.getPosition()).isPortal()){

				AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
				Iterator<Perception> objs = frustrum.objects();
	
				/* The mission of the nurse is to heal injured ants and to feed babies. */
				boolean mission = false;
				Perception goal = null;
				
				if (objs == null || !objs.hasNext())
					return wander();
				
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
			}else{
				//The cell is a portal
				PortalCell cell = (PortalCell) body.getEnvironment().getCell(body.getPosition());
				influence = new MotionInfluence(body, cell.getArrivalPosition(), cell.getArrivalEnvironment());
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
	private MotionInfluence heal(AbstractBody bodyTarget){
		boolean goodPosition = this.isOnSamePosition(bodyTarget);
		MotionInfluence influence;
		
		if(goodPosition){
			InterfaceNurse interfaceNurse = (InterfaceNurse) this.getBody();
			AttackCureInfluence cureInfluence = new AttackCureInfluence(bodyTarget, interfaceNurse.getStdCure());
			body.addInfluenceHere(cureInfluence);
			
			influence = null;
		}else{
			//move to goal
			MyPoint2D goalPos = bodyTarget.getPosition();
			influence = new MotionInfluence(bodyTarget, goalPos, bodyTarget.getEnvironment());
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
