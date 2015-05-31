package env2.instanciator.actions;

import env2.api.AbstractAction;
import env2.api.AbstractInfluence;

public abstract class AbstractActionInstanciator {

	public static AbstractInfluence influence; 
	
	public abstract AbstractAction getAction();
}
