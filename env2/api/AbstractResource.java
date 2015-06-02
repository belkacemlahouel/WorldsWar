package env2.api;

public abstract class AbstractResource extends AbstractWorldObject {
	public abstract int getQuantity();
	public abstract AbstractResource pick(int quantity);
	public abstract void add(int quantity);
	
	/***/
	
	private int NB_PICKER = 0;
	
	public void addPicker() {
		++NB_PICKER;
	}
	
	public void resetNbPicker() {
		NB_PICKER = 0;
	}
	
	public int getNbPickers() {
		return NB_PICKER;
	}
}
