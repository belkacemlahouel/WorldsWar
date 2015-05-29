package env2.influences;

import java.util.Collection;

import env2.api.AbstractInfluence;
import env2.api.AbstractWorldObject;
import env2.type.InfluenceType;

public class RemoveMeInfluence extends AbstractInfluence {

	public AbstractWorldObject TO_REMOVE;
	public final Collection<AbstractWorldObject> CONTAINER;
	
	public RemoveMeInfluence(AbstractWorldObject to_be_removed, Collection<AbstractWorldObject> container) {
		CONTAINER = container;
		TO_REMOVE = to_be_removed;
	}
	
	@Override
	public InfluenceType getType() {
		return InfluenceType.REMOVE_ME;
	}
}
