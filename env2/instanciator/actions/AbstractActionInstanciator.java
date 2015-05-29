package env2.instanciator.actions;

import env2.api.AbstractAction;
import env2.api.AbstractBody;

public abstract class AbstractActionInstanciator {

	public static AbstractBody ACTOR;
	public static AbstractBody TARGET;
	public static int LIFE_VAR;
	
	public abstract AbstractAction getAction();
}
