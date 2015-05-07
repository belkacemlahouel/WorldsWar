package parser;

class BodyInfo{
	ConfParameters type;
	ConfParameters function;
	int tribeId;
	int env;
	int pos[] = new int[2];
	int quantity;
	
	
	BodyInfo()
	{
		quantity = 0;
	}
	
	/**
	 * Print the informations about bodies.
	 */
	public void printInfos()
	{
		if(quantity != 0)
		{
			System.out.println("Initialisation of "+quantity +" "+ function +" "+ type + " in env " + env + " at position " + pos[0] + ","+pos[1]+"in tribe "+tribeId);
		}
		else
		{
			System.out.println("Initialisation of " + function +" "+ type + " in env " + env + " at position " + pos[0] + ","+pos[1]+"in tribe "+tribeId);
		}
	}
}