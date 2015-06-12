package sim.agent.antagent;

import java.util.Iterator;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceGatherer;
import env2.api.InterfaceUndertaker;
import env2.body.antbody.AntUndertakerBody;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.Perception;
import env2.influences.BuryDeadInfluence;
import env2.influences.EatInfluence;
import env2.influences.MotionInfluence;
import env2.type.Time;
import env2.type.WorldObjectType;

/**
 * The only goal of an undertaker is to burry dead body of its friends.
 * @author romain
 *
 */
public final class AntUndertakerAgent extends AntAgent {
	
	public AntUndertakerAgent(AntUndertakerBody b, int ID) {
		super(b, ID);
		// TODO Auto-generated constructor stub
	}

	public MotionInfluence live() {
		
		if (body.isDead()) {
			//System.out.println("I'm dead: " + body.getPosition());
			return null;
		}
		
		if (body.isBaby(Time.getTime())) {
			return null;
		}
		
		for (AbstractWorldObject o : body.getCell().getObjects()) {
			if (WorldObjectType.isAntBody(o.getType())) {
				AbstractBody antBody = (AbstractBody) o;
				//System.out.println(antBody.isDead());
				if(antBody.isFriend(body) && antBody.isDead()){
					InterfaceUndertaker undertaker = (InterfaceUndertaker)body;
					BuryDeadInfluence eat = new BuryDeadInfluence(undertaker,antBody);
					body.addInfluence(eat);
					return null;
				}
			}
		}
		
		body.buildNewFrustrum();
		MotionInfluence influence = null;
		AbstractFrustrum frustrum = body.getCurrentFrustrum();
		Iterator<Perception> objs = frustrum.objects();
		
		while(objs.hasNext()){
			Perception objectDetected = objs.next();
			
			if(WorldObjectType.isAntBody(objectDetected.object.getType())){
				
				AbstractBody antBody = (AbstractBody) objectDetected.object;
				//System.out.println(antBody.isDead());
				if(antBody.isFriend(body) && antBody.isDead()){
					influence = burryDeadFriend(objectDetected.object);
				}
				
			}
		}
		
		/* Get the wander motion direction */
		if(influence == null){
			influence = wander();
		}
		
		return influence;
	}
	
	
	/**
	 * Burry a dead friend ant.
	 */
	private MotionInfluence burryDeadFriend(AbstractWorldObject friend){
		MotionInfluence motion = null;
		boolean goodPosition = this.isOnSamePosition(friend);
		
		if(goodPosition){
			InterfaceUndertaker undertaker = (InterfaceUndertaker)body;
			BuryDeadInfluence eat = new BuryDeadInfluence(undertaker,(AbstractBody)friend);
			body.addInfluence(eat);
			System.out.println("burry");
		}else{
			MyPoint2D goalPos = ((AbstractBody)friend).getPosition();
			motion = new MotionInfluence(body, goalPos, body.getEnvironment());
		}
		
		return motion;
	}
}
