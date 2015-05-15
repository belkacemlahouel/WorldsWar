package env2.env;

import env2.api.AbstractEnvironment;

/**
 * This class may seem useless,
 * but I am thinking about adding
 * other methods inside to make it useful
 * *** ENHANCEMENT ***
 * 
 * @author belka
 *
 */

public class Environment {

	private AbstractEnvironment[] grounds;
	
	public Environment(int size) {
		grounds = new AbstractEnvironment[size];
	}
	
	public AbstractEnvironment get(int i) {
		return grounds[i];
	}
}
