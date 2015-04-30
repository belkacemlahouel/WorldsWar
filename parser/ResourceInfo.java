package parser;

class ResourceInfo{
	String type;
	int env;
	int posX;
	int posY;
	int quantity;
	
	/**
	 * Print the informations about a resource.
	 */
	public void printInfos()
	{
		System.out.println(quantity + " resource(s) of " + type + " in env " + env + " in position " + posX +","+posY);
	}
}