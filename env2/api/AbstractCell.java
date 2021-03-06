package env2.api;

import java.util.LinkedList;
import java.util.List;

import env2.influences.PickInfluence;
import env2.influences.PutInfluence;
import env2.instanciator.actions.AbstractActionInstanciator;
import env2.instanciator.factory.ActionFactory;
import env2.type.InfluenceType;

public abstract class AbstractCell {

	// public abstract AbstractEnvironment getEnvironment();
	public abstract List<AbstractWorldObject> getObjects();
	public abstract boolean removeObject(AbstractWorldObject o);
	public abstract void addObject(AbstractWorldObject o);
	public abstract boolean isPortal();
	
	/***/
	private List<AbstractInfluence> myinfluences = new LinkedList<>();
	
	public List<AbstractAction> solveInfluences() {
		List<AbstractAction> actions = new LinkedList<>();
		
		if (myinfluences == null || myinfluences.isEmpty())
			return actions;
		
		for (AbstractInfluence influence : myinfluences) {
			switch (influence.getType()) {
			case DROP_PHEROMONE:
				AbstractActionInstanciator.influence = influence;
				actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.DROP_PHEROMONE).getAction());
				break;
				
			case PICK:
				PickInfluence pick_influence = (PickInfluence) influence;
				pick_influence.qty /= pick_influence.picked.getNbPickers();
				pick_influence.qty = Math.max(0, Math.min(pick_influence.qty, pick_influence.picker.getStrength()));
				// TODO bounded with strength or another formula?
				if (pick_influence.picked.incrNbPickersServed() >= pick_influence.picked.getNbPickers())
					pick_influence.picked.resetNbPickers();
				AbstractActionInstanciator.influence = pick_influence;
				actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.PICK).getAction());
				break;
				
			case PUT:
				AbstractActionInstanciator.influence = influence;
				actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.PUT).getAction());
				break;
				
			case REMOVE_ME:
				AbstractActionInstanciator.influence = influence;
				actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.REMOVE_ME).getAction());
				break;
				
			default:
				System.out.println("Influence on cell not treated: " + influence);
			}
		}
		
		myinfluences.clear();		
		return actions;
	}
	
	public void addInfluence(AbstractInfluence influence) {
		myinfluences.add(influence);
	}
	
	/***/
	
	private boolean _walkable;
	
	public boolean isWalkable() {
		return _walkable;
	}
	
	public void setWalkable(boolean walkable) {
		_walkable = walkable;
	}
}
