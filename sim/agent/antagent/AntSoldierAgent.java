package sim.agent.antagent;

import java.util.Iterator;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractWorldObject;
import env2.body.antbody.AntSoldierBody;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.Perception;
import env2.influences.MotionInfluence;

/**
 * The mission of the soldier is to fight enemy.
 * This soldier is implemented to act like a berseker : only searching for fight (so first aims to enemy and secondary searching for danger pheromones).
 */
public final class AntSoldierAgent extends AntAgent {

	public AntSoldierAgent(AntSoldierBody b, int ID) {
		super(b, ID);
		// TODO Auto-generated constructor stub
	}	
	
	/**
	 * Implementation of the basic behavior of a soldier ant.
	 */
	public MotionInfluence live() {
		AbstractBody body = this.getBody();
		MotionInfluence influence = null;

		if(body.isBaby(Time.getTime())){
			AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
			Iterator<Perception> objs = frustrum.objects();
			
			boolean mission = false;
			/* The goal could represent an enemy or a DANGERPHEROMONE */
			AbstractWorldObject goal = null;
			
			while(objs.hasNext() && mission==false){
				AbstractWorldObject obj = objs.next().object;
				
				/* Case where obj is a body (so a potential enemy */
				if(WorldObjectType.isAntBody(obj.getType()) || WorldObjectType.isTermiteBody(obj.getType())){
					if(!((AbstractBody) obj).isFriend(this.getBody())){
						goal = obj;
						dropPheromone(WorldObjectType.DANGERPHEROMONE);
						mission = true;
					}
				}else if(WorldObjectType.isPheromone(obj.getType())){
					goal = obj;
				}
			}
			
			/* Behavior in function of the result of parsing the frustrum. */
			if(goal == null){
				influence = wander();
			}else{
				influence = reachGoal(goal);
			}
		}
		
		return influence;		
	}
	
	
	/**
	 * Behaviour of the ant soldier when it needs to drop a pheromone.
	 * @param pheromone, the type of pheromone to drop.
	 */
	private void dropPheromone(WorldObjectType pheromone){
		if (pheromone == WorldObjectType.DANGERPHEROMONE){
			this.getBody().producePheromoneDanger();
		}else if (pheromone == WorldObjectType.FOODPHEROMONE){
			this.getBody().producePheromoneFood();
		}
	}
	
	
	/**
	 * Behaviour of the soldier gatherer when it wants to reach a goal.
	 */
	private MotionInfluence reachGoal(AbstractWorldObject goal){
		boolean goodPosition = this.isOnSamePosition(goal);
		MotionInfluence influence;
		
		if(goodPosition){
			influence = null;
					
			/* If it is a pheromone, the soldier doesn't do anything, it waits for a fight or for the pheromone to disappear. */
			if(WorldObjectType.isAntBody(goal.getType()) || WorldObjectType.isTermiteBody(goal.getType())){
			//TODO : add action fight
			}
		}else{
			//move to goal
			MyPoint2D goalPos = null;
			influence = new MotionInfluence(body, goalPos, body.getEnvironment());
		}
		
		return influence;
	}
	
}
