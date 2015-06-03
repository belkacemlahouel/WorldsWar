package sim.agent.antagent;

import java.util.Iterator;

import math.MyPoint2D;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.api.AbstractBody;
import env2.api.AbstractWorldObject;
import env2.body.antbody.AntGathererBody;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.Perception;
import env2.influences.MotionInfluence;

/**
 * Implementation of an gatherer ant.
 * The priorities in its behavior are basically the followings : get food, follow food pheromones, avoid danger (other agent first, and pheromones in second), randomly search food.
 * TODO : test goal position in action function : if agent is in the good position do action, if not, move to goal.
 */
public final class AntGathererAgent extends AntAgent {

	public AntGathererAgent(AntGathererBody b, int ID) {
		super(b, ID);
	}

	/**
	 * Implementation of the basic behavior of a gatherer ant.
	 */
	public MotionInfluence live() {
		MotionInfluence influence = null;

		if(!this.getBody().isBaby(Time.getTime())){
			AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
			Iterator<Perception> objs = frustrum.objects();

			/* The mission of the gatherer is to find food. If it finds one resource eatable, it directly goes to it. */
			boolean mission = false;
			Perception goal = null;
			
			while(objs.hasNext() && mission==false){
				Perception objWithPos = objs.next();
				AbstractWorldObject obj = objWithPos.object;
				
				/* obj is a resource */
				if(WorldObjectType.canBeFood(obj.getType()))
				{
					switch(this.getBody().getEffect(obj)){
						case GOOD:
							goal = objWithPos;
							mission = true;
							dropPheromone(WorldObjectType.FOODPHEROMONE);
							break;
						case VERYGOOD:
							goal = objWithPos;
							mission = true;
							dropPheromone(WorldObjectType.FOODPHEROMONE);
							break;
						default:
							break;
					}
				}else if(WorldObjectType.isPheromone(obj.getType())){
					/* obj is a pheromon*/
					switch(obj.getType()){
						case DANGERPHEROMONE:
							if(goal==null)
							{
								goal = objWithPos;
							}
							break;
						case FOODPHEROMONE:
							if(goal==null)
							{
								goal = objWithPos;
							}
							else if(goal.object.getType() == WorldObjectType.DANGERPHEROMONE){
								goal = objWithPos;
							}
							break;
						default:
							break;
					}
				}else if(WorldObjectType.isAntBody(obj.getType()) || WorldObjectType.isTermiteBody(obj.getType())){
					if(!((AbstractBody) obj).isFriend(this.getBody())){
						if(goal == null){
							goal = objWithPos;
							dropPheromone(WorldObjectType.DANGERPHEROMONE);
						}else if (!WorldObjectType.canBeFood(goal.object.getType()) || goal.object.getType() != WorldObjectType.FOODPHEROMONE){
							goal = objWithPos;
							dropPheromone(WorldObjectType.DANGERPHEROMONE);
						}
					}
				}
				
				/* Behavior in function of the result of parsing the frustrum. */
				if(goal == null){
					influence = wander();
				}
				else if (goal.object.getType()==WorldObjectType.DANGERPHEROMONE){
					influence = avoidDanger(goal);
				}
				else{
					influence = reachGoal(goal);
				}
			}
		}
		
		return influence;
	}
	
	/**
	 * Behaviour of the ant gatherer when it wants to avoid a danger.
	 */
	private MotionInfluence avoidDanger(Perception goal){
		MotionInfluence influence;
		MyPoint2D goalPos = goal.position;
		goalPos.setLocation(-goalPos.getX(), -goalPos.getY());

		influence = new MotionInfluence(body, goalPos, body.getEnvironment());
		
		return influence;
	}
	
	/**
	 * Behaviour of the ant gatherer when it wants to reach a goal.
	 */
	private MotionInfluence reachGoal(Perception goal){
		MotionInfluence influence;
		AbstractBody body = this.getBody();
		boolean goodPosition = this.isOnSamePosition(goal.object);
		
		if(goodPosition){
			influence = null;
			//TODO : add action take food
		}else{
			//move to goal
			MyPoint2D goalPos = goal.position;
			influence = new MotionInfluence(body, goalPos, body.getEnvironment());
		}
		
		return influence;
	}
	
	/**
	 * Behaviour of the ant gatherer when it needs to drop a pheromone.
	 * @param pheromone, the type of pheromone to drop.
	 */
	private void dropPheromone(WorldObjectType pheromone){
		if (pheromone == WorldObjectType.DANGERPHEROMONE){
			this.getBody().producePheromoneDanger();
		}else if (pheromone == WorldObjectType.FOODPHEROMONE){
			this.getBody().producePheromoneFood();
		}
	}
}
