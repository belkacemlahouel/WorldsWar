package parser;

import env2.type.WorldObjectType;

public class ResourceInfo extends AbstractInfo {
	
	public WorldObjectType type;
	public int env;
	public int posX;
	public int posY;
	public int quantity;
	
	/**
	 * Print the informations about a resource.
	 */
	public void printInfos() {
		System.out.println(quantity + " resource(s) of " + type + " in env " + env + " in position " + posX + ", " + posY);
	}
}