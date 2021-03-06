package sim.agent.antagent;

import java.util.Collection;
import java.util.HashMap;
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
import env2.influences.EatInfluence;
import env2.influences.MotionInfluence;
import env2.influences.PickInfluence;
import env2.influences.PutInfluence;

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
		
		// Test if the ant is dead
		if (body.isDead()) {
			//System.out.println("I'm dead: " + body.getPosition());
			return null;
		}
		
		// Test if the ant is full
		if(body.isFull()){
			//System.out.println("I'm full !");
			dropPheromone(WorldObjectType.FOODPHEROMONE);
			return returnCave();
		}
		
		//If the ant is a baby, it can't do anything.
		if(!body.isBaby(Time.getTime())){
			//test if the cell is a portal
			if(!body.getEnvironment().getCell(body.getPosition()).isPortal()){
				
				
				for (AbstractWorldObject o : body.getCell().getObjects()) {
					if (WorldObjectType.canBeFood(o.getType())) {
						if (body.getEffect(o).value >= EffectType.GOOD.value) {
							int danger = body.getMaxLife()/2;
							if(body.getLife()<=danger){
								InterfaceGatherer interfaceGath = (InterfaceGatherer)body;
								System.out.println("eat");
								EatInfluence eat = new EatInfluence(body, getBody().getEnvironment().getCell(getBody().getPosition()).getObjects(), (AbstractResource) o, interfaceGath.getStdTakeQty());
								body.addInfluence(eat);
							}else{
								PickInfluence pickInfluence = new PickInfluence((InterfaceGatherer) body,
																body.getCell().getObjects(),
																(AbstractResource) o,
																((InterfaceGatherer) body).getStdTakeQty());
								body.addInfluenceHere(pickInfluence);
							}
							return null;
						}
					}
				}
				
				body.buildNewFrustrum();
				AbstractFrustrum frustrum = body.getCurrentFrustrum();
				Iterator<Perception> objs = frustrum.objects();
				
//				System.out.println("***" + body.getPosition() + " " + body.getType());
//				while (objs.hasNext()) {
//					System.out.print(((objs.next().object.getType() == WorldObjectType.ANTUNDERTAKERBODY) ? "\t+\n" : ""));
//				}
				
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
						if(!((AbstractBody) obj).isFriend(body)){
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
			int danger = body.getMaxLife()/2;
			
			if(body.getLife()<=danger){
				EatInfluence eat = new EatInfluence(body, getBody().getEnvironment().getCell(getBody().getPosition()).getObjects(), (AbstractResource) goal.object, interfaceGath.getStdTakeQty());
				body.addInfluence(eat);
			}else{
				PickInfluence pick = new PickInfluence(interfaceGath, getBody().getEnvironment().getCell(getBody().getPosition()).getObjects(), (AbstractResource) goal.object, interfaceGath.getStdTakeQty());
				body.addInfluenceHere(pick);
			}
			
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
	 * Behaviour of the ant gatherer when it wants to return to its cave.
	 */
	private MotionInfluence returnCave(){
		MotionInfluence influence = null;
		
		body.buildNewFrustrum();
		AbstractFrustrum frustrum = body.getCurrentFrustrum();
		Iterator<Perception> objs = frustrum.objects();
		
		MyPoint2D goal = body.getPosition();
		int nbAnts = 0;
		HashMap<MyPoint2D,Integer> cells = new HashMap<MyPoint2D,Integer>();
		
		/* Search all the cell with ants from its tribe. */
		while(objs.hasNext()){
			Perception objectDetected = objs.next();
			WorldObjectType type = objectDetected.object.getType();
			//System.out.println(type + " in " + objectDetected.position);
			
			if(WorldObjectType.isAntBody(type)){
				if(((AbstractBody) objectDetected.object).isFriend(body)){
					
					/* The perceived ant is the queen, it must put the resources here. */
					if(type==WorldObjectType.ANTMOTHERBODY){
						//put resources on the ground
						InterfaceGatherer interfaceGath = (InterfaceGatherer)body;
						PutInfluence put = new PutInfluence(interfaceGath,body.getCargo().get(0),body.getTransportCapacity());
						body.addInfluenceHere(put);
						System.out.println("PUT!");
						return null;
					}
					
					/* The perceived ant is a friend */
					if(cells.containsKey(objectDetected.position)){
						cells.put(objectDetected.position, cells.get(objectDetected.position) + 1);
					}
					else{
						cells.put(objectDetected.position, 1);
					}
				}
			}
		}
		
		/* Search for the cell that contains the more ants from its tribe. */
		int tmp;
		for (MyPoint2D key : cells.keySet())
		{
		    tmp = cells.get(key);
		    if(tmp>nbAnts){
		    	goal = key;
		    	nbAnts = tmp;
		    }
		}
		//System.out.println("Move to : "+goal.getX()+","+goal.getY());
		influence = new MotionInfluence(body, goal, body.getEnvironment());
		
		return influence;
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
