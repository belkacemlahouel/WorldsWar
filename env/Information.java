package env;

public abstract class Information {
	
	/**
	 * @return type of information contained
	 */
	public abstract InformationType type();
	
	/**
	 * @return spread distance (speed) of the information
	 */
	public abstract int distance();

	/**
	 * @return quantity of information
	 */
	protected abstract int quantity();
	
	/**
	 * @return remaining time before this information dies
	 */
	protected abstract int time();
	
	/**
	 * @return strength of any information in the environment
	 * f(quantity, time)
	 */
	public abstract int strength();
}

enum InformationType {
	SEXUAL,
	FIGHT,
	FOOD;
	// TODO Fill this list up
}

class AntPheromone extends Information {

	@Override
	protected int quantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int time() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int strength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InformationType type() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int distance() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class TermitePheromone extends Information {

	@Override
	protected int quantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int time() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int strength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InformationType type() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int distance() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class SpiderPheromone extends Information {

	@Override
	public InformationType type() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int quantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int time() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int strength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int distance() {
		// TODO Auto-generated method stub
		return 0;
	}	
}
