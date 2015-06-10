package sim.agent.antagent;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import math.MyPoint2D;
import env2.type.EffectType;
import env2.type.Time;
import env2.type.WorldObjectType;
import env2.api.AbstractBody;
import env2.api.AbstractCell;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceGatherer;
import env2.body.antbody.AntGathererBody;
import env2.env.PortalCell;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.Perception;
import env2.influences.MotionInfluence;
import env2.influences.PickInfluence;

/**
 * Implementation of an gatherer ant.
 * The priorities in its behavior are basically the following:
 * 		get food,
 * 		follow food pheromones,
 * 		avoid danger (other agent first, and pheromones in second),
 * 		randomly search food.
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
		
		/*if (body.isBaby(Time.getTime())) {
			System.out.println("I'm a baby :)");
			return null;
		}*/
		
		if (body.isDead()) {
			System.out.println("I'm dead: " + body.getPosition());
			return null;
		}
		
		if(body.getTransportCapacity()==0){
			System.out.println("I'm full !");
		}
		
		if(!body.isBaby(Time.getTime())){
			//test if the cell is a portal
			if(!body.getEnvironment().getCell(body.getPosition()).isPortal()){
				
				
				for (AbstractWorldObject o : body.getCell().getObjects()) {
					if (WorldObjectType.canBeFood(o.getType())) {
						if (body.getEffect(o).value >= EffectType.GOOD.value) {
							PickInfluence pickInfluence = new PickInfluence((InterfaceGatherer) body,
															body.getCell().getObjects(),
															(AbstractResource) o,
															((InterfaceGatherer) body).getStdTakeQty());
							body.addInfluenceHere(pickInfluence);
							return null;
						}
					}
				}
				
				
				AbstractFrustrum frustrum = this.getBody().getCurrentFrustrum();
				Iterator<Perception> objs = frustrum.objects();
				
				if (objs == null || !objs.hasNext())
					return wander();
	
				/* The mission of the gatherer is to find food. If it finds one resource eatable, it directly goes to it. */
				boolean mission = false;
				Perception goal = null;
				
				while(objs.hasNext() && mission==false){
					Perception objWithPos = objs.next();
					AbstractWorldObject obj = objWithPos.object;
					
					/* obj is a resource */
					if(WorldObjectType.canBeFood(obj.getType()))
					{
						if (body.getEffect(obj).value >= EffectType.GOOD.value) {
							goal = objWithPos;
							mission = true;
							dropPheromone(WorldObjectType.FOODPHEROMONE);
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
			}else{
				//The cell is a portal
				PortalCell cell = (PortalCell) body.getEnvironment().getCell(body.getPosition());
				influence = new MotionInfluence(body, cell.getArrivalPosition(), cell.getArrivalEnvironment());
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
			
			InterfaceGatherer interfaceGath = (InterfaceGatherer)body;
			// Collection<AbstractResource> collectionResources = this.getResourceCollection(); // FIXME Check container
			
			// EatInfluence eat = new EatInfluence(body, collectionResources, (AbstractResource) goal.object, interfaceGath.getStdTakeQty());
			
			PickInfluence pick = new PickInfluence(interfaceGath, getBody().getEnvironment().getCell(getBody().getPosition()).getObjects(), (AbstractResource) goal.object, interfaceGath.getStdTakeQty());
			
			// body.addInfluence(eat);
			body.addInfluenceHere(pick);
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
		
		// FIXME Drop influence of droping!
		
		if (pheromone == WorldObjectType.DANGERPHEROMONE){
			this.getBody().producePheromoneDanger();
		}else if (pheromone == WorldObjectType.FOODPHEROMONE){
			this.getBody().producePheromoneFood();
		}
	}
	
	
	/**
	 * Get the resources in the cell of the body.
	 * @return collection of resources.
	 */
	private Collection<AbstractResource> getResourceCollection(){
		Collection<AbstractResource> collection = new LinkedList<AbstractResource>();
		
		AbstractCell cell = this.getBody().getEnvironment().getCell(body.getPosition());
		List<AbstractWorldObject> objects = cell.getObjects();
		
		Iterator<AbstractWorldObject> iterator = objects.iterator();
		AbstractWorldObject obj;
		
		while(iterator.hasNext()){
			obj = iterator.next();
			WorldObjectType type = obj.getType();
			if(!WorldObjectType.isAntBody(type) && !WorldObjectType.isPheromone(type) && !WorldObjectType.isTermiteBody(type))
			{
				collection.add((AbstractResource) obj);
			}
		}
		
		return collection;
	}
}
