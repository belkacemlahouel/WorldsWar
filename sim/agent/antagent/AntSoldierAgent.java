package sim.agent.antagent;

import java.util.Collection;
import java.util.Iterator;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceGatherer;
import env2.api.InterfaceSoldier;
import env2.body.antbody.AntSoldierBody;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.Perception;
import env2.influences.AttackCureInfluence;
import env2.influences.EatInfluence;
import env2.influences.MotionInfluence;
import env2.influences.PickInfluence;

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
		
		if (body.isDead()) {
			System.out.println("I'm dead: " + body.getPosition());
			return null;
		}

		if(body.isBaby(Time.getTime())){
			
			//test if the cell is a portal
			if(!body.getEnvironment().getCell(body.getPosition()).isPortal()){
				
				AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
				Iterator<Perception> objs = frustrum.objects();
				
				if (objs == null || !objs.hasNext())
					return wander();
				
				boolean mission = false;
				/* The goal could represent an enemy or a DANGERPHEROMONE */
				Perception goal = null;
				
				while(objs.hasNext() && mission==false){
					Perception objWithPos = objs.next();
					AbstractWorldObject obj = objWithPos.object;
					
					/* Case where obj is a body (so a potential enemy */
					if(WorldObjectType.isAntBody(obj.getType()) || WorldObjectType.isTermiteBody(obj.getType())){
						if(!((AbstractBody) obj).isFriend(this.getBody())){
							goal = objWithPos;
							dropPheromone(WorldObjectType.DANGERPHEROMONE);
							mission = true;
						}
					}else if(WorldObjectType.isPheromone(obj.getType())){
						goal = objWithPos;
					}
				}
				
				/* Behavior in function of the result of parsing the frustrum. */
				if(goal == null){
					influence = wander();
				}else{
					influence = reachGoal(goal);
				}
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
	private MotionInfluence reachGoal(Perception goal){
		boolean goodPosition = this.isOnSamePosition(goal.object);
		MotionInfluence influence;
		
		if(goodPosition){
			influence = null;
					
			/* If it is a pheromone, the soldier doesn't do anything, it waits for a fight or for the pheromone to disappear. */
			if(WorldObjectType.isAntBody(goal.object.getType()) || WorldObjectType.isTermiteBody(goal.object.getType())){
				InterfaceSoldier soldier = (InterfaceSoldier) body;		
				AttackCureInfluence attack = new AttackCureInfluence((AbstractBody) goal.object, soldier.getRndDmg());				
				body.addInfluenceHere(attack);
			}
		}else{
			//move to goal
			MyPoint2D goalPos = goal.position;
			influence = new MotionInfluence(body, goalPos, body.getEnvironment());
		}
		
		return influence;
	}
	
}
