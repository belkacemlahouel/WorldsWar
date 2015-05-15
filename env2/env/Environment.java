package env2.env;

import java.util.NoSuchElementException;

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
	
	public void set(int i, AbstractEnvironment env) {
		if (i >= grounds.length)
			throw new NoSuchElementException("Index specified is beyond array size.");
		
		grounds[i] = env;
	}
}
