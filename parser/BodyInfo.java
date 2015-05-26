package parser;

import env2.type.WorldObjectType;

public class BodyInfo extends AbstractInfo {
	
	public WorldObjectType function;
	public int tribeId;
	public int env;
	public int[] pos;
	public int quantity;
	
	public BodyInfo() {
		pos = new int[2];
		quantity = 0;
	}
	
	/**
	 * Prints the information about bodies.
	 */
	public void printInfos() {
		if(quantity != 0) {
			System.out.println("Initialisation of " + quantity + " " + function + " in env " + env + " at position " + pos[0] + ", " + pos[1] + " in tribe " + tribeId);
		} else {
			System.out.println("Initialisation of " + function + " in env " + env + " at position " + pos[0] + ", " + pos[1] + " in tribe " + tribeId);
		}
	}
}