package gui;

import env2.api.AbstractEnvironment;
import env2.env.Ground;

public class MainGUI {

	public static void main(String[] args) {		
		AbstractEnvironment env = new Ground(50, 50);
		GUI window = new GUI(env);
	}
}
