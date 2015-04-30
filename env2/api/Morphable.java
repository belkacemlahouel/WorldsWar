package env2.api;

/*
 * 
 */

public interface Morphable {

	
	public default boolean tic(){
		tac();
		if (getCount() <= 0){
			this.morph();
			return true;
		}
		return false;
	}
	
	abstract int getCount(); 
	abstract void tac();
	abstract boolean morph();
}
