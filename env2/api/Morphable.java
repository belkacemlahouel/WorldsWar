package env2.api;

/*
 * 
 */

public interface Morphable {

	
	public default boolean tic(){
		tac();
		if (getCount() <= 0){
			return this.morph();
		}
		return false;
	}
	
	abstract int getCount(); 
	abstract void tac();
	
	/*
	 * morph return true if the element has to be delete after the tic function
	 */
	abstract boolean morph();
}
