package env2.pheromones;

class Pheromons {
	private PheromonsType type;
	private int remainingTime;
	
	Pheromons(PheromonsType pType)
	{
		type = pType;
		remainingTime = getMaxLapse();
	}
	
	private int getMaxLapse()
	{
		return 30;
	}
}