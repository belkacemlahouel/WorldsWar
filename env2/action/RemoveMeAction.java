package env2.action;

import java.util.Collection;

import env2.api.AbstractAction;
import env2.api.AbstractWorldObject;

public class RemoveMeAction extends AbstractAction {

	private AbstractWorldObject TO_REMOVE;
	private final Collection<AbstractWorldObject> CONTAINER;
	
	public RemoveMeAction(AbstractWorldObject to_be_removed, Collection<AbstractWorldObject> container) {
		CONTAINER = container;
		TO_REMOVE = to_be_removed;
	}
	
	public void doAction() {
		if (CONTAINER != null && !CONTAINER.isEmpty())
			CONTAINER.remove(TO_REMOVE);
		
		TO_REMOVE = null;
	}
}
