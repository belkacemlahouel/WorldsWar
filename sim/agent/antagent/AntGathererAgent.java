package sim.agent.antagent;

import java.util.Iterator;

import env2.type.Direction;
import env2.type.WorldObjectType;
import env2.api.AbstractFrustrum;
import env2.api.AbstractWorldObject;
import env2.body.antbody.AntGathererBody;
import env2.type.EffectType;

/**
 * Implementation of an gatherer ant.
 * The priorities in its behavior are basically the followings : get food, follow food pheromones, avoid danger, randomly search food.
 *
 */
public final class AntGathererAgent extends AntAgent {

	public AntGathererAgent(AntGathererBody b, int ID) {
		super(b, ID);
	}

	/**
	 * Implementation of the basic behavior of a gatherer ant.
	 */
	public void live() {
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
					break;
				case VERYGOOD:
					goal = obj;
					mission = true;
					break;
				default:
					break;
				}
			}
			
			/* obj is a pheromon*/
			if(WorldObjectType.isPheromone(obj.getType()))
			{
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
	
	/**
	 * Behaviour of the ant gatherer when it hasn't find anything.
	 */
	public void searchMotion(){
		float angle = (float) ((Math.random() - Math.random())/Math.PI * 4);
		Direction direction = this.getBody().getDirection();
		//TODO finish it
	}
	
	/**
	 * Behaviour of the ant gatherer when it wants to avoid a danger.
	 */
	public void avoidDanger(AbstractWorldObject goal){
		
	}
	
	/**
	 * Behaviour of the ant gatherer when it wants to reach a goal.
	 */
	public void reachGoal(AbstractWorldObject goal){
		
	}
}
