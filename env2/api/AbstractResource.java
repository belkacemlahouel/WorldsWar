package env2.api;

public abstract class AbstractResource extends AbstractWorldObject {
	
	public abstract int getQuantity();
	public abstract AbstractResource pick(int quantity);
	public abstract void add(int quantity);
	
	/***/
	
	private int NB_PICKER = 0;
	private int NB_PICKER_SERVED = 0;
	
	public void addPicker() {
		++NB_PICKER;
	}
	
	public void resetNbPickers() {
		NB_PICKER = 0;
		NB_PICKER_SERVED = 0;
	}
	
	public int getNbPickers() {
		return NB_PICKER;
	}
	
	public int incrNbPickersServed() {
		return (++NB_PICKER_SERVED);
	}
	
	/***/
	
	/* public AbstractResource getShare() {
		return pick();
	} */
	
	// TODO I need to use resetNbPickers() somewhere.
	// TODO What if I want to take something less than quantity/nbpickers
}
