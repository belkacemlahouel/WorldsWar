package sim.agent.antagent;

import java.util.Iterator;

import env2.type.Direction;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.api.AbstractBody;
import env2.api.AbstractFrustrum;
import env2.api.AbstractWorldObject;
import env2.body.antbody.AntGathererBody;
import env2.type.EffectType;

/**
 * Implementation of an gatherer ant.
 * The priorities in its behavior are basically the followings : get food, follow food pheromones, avoid danger (other agent first, and pheromones in second), randomly search food.
 *
 */
public final class AntGathererAgent extends AntAgent {

	public AntGathererAgent(AntGathererBody b, int ID) {
		super(b, ID);
	}

	/**
	 * Implementation of the basic behavior of a gatherer ant.
	 * TODO : add dropPheromone(danger) when find an opponant+droppheromone(food) when find food.
	 */
	public void live() {
		if(!this.getBody().isBaby(Time.TIME)){
			AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
			Iterator<AbstractWorldObject> objs = frustrum.objects();
			/* The mission of the gatherer is to find food. If it finds one resource eatable, it directly goes to it. */
			boolean mission = false;
			AbstractWorldObject goal = null;
			
			while(objs.hasNext() && mission==false){
				AbstractWorldObject obj = objs.next();
				
				/* obj is a resource */
				if(WorldObjectType.canBeFood(obj.getType()))
				{
					switch(this.getBody().getEffect(obj)){
						case GOOD:
							goal = obj;
							mission = true;
							dropPheromone(WorldObjectType.FOODPHEROMONE);
							break;
						case VERYGOOD:
							goal = obj;
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
								goal = obj;
							}
							break;
						case FOODPHEROMONE:
							if(goal==null)
							{
								goal = obj;
							}
							else if(goal.getType() == WorldObjectType.DANGERPHEROMONE){
								goal = obj;
							}
							break;
						default:
							break;
					}
				}else if(WorldObjectType.isAntBody(obj.getType()) || WorldObjectType.isTermiteBody(obj.getType())){
					if(!((AbstractBody) obj).isFriend(this.getBody())){
						if(goal == null){
							goal = obj;
							dropPheromone(WorldObjectType.DANGERPHEROMONE);
						}else if (!WorldObjectType.canBeFood(goal.getType()) || goal.getType() != WorldObjectType.FOODPHEROMONE){
							goal = obj;
							dropPheromone(WorldObjectType.DANGERPHEROMONE);
						}
					}
				}
				
				/* Behavior in function of the result of parsing the frustrum. */
				if(goal == null){
					searchMotion();
				}
				else if (goal.getType()==WorldObjectType.DANGERPHEROMONE){
					avoidDanger(goal);
				}
				else{
					reachGoal(goal);
				}
			}
		}
	}
	
	/**
	 * Behaviour of the ant gatherer when it hasn't find anything.
	 */
	private void searchMotion(){
		float angle = (float) ((Math.random() - Math.random())/Math.PI * 4);
		Direction direction = this.getBody().getDirection();
		//TODO must be change, in function of the kinematic that will be implemented.
		//influenceKinemtic(direction, angle);
	}
	
	/**
	 * Behaviour of the ant gatherer when it wants to avoid a danger.
	 */
	private void avoidDanger(AbstractWorldObject goal){
		
	}
	
	/**
	 * Behaviour of the ant gatherer when it wants to reach a goal.
	 */
	private void reachGoal(AbstractWorldObject goal){
		
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
