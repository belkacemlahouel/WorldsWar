package parser;

public class BodyInfo extends AbstractInfo {
	
	public ConfParameters type;
	public ConfParameters function;
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
			System.out.println("Initialisation of " + quantity + " " + function + " " + type + " in env " + env + " at position " + pos[0] + ", " + pos[1] + " in tribe " + tribeId);
		} else {
			System.out.println("Initialisation of " + function + " " + type + " in env " + env + " at position " + pos[0] + ", " + pos[1] + " in tribe " + tribeId);
		}
	}
}