package gui;

import env.environment.Environment;
import env.environment.EnvironmentGround;

public class MainGUI {

	public static void main(String[] args) {		
		Environment env = new EnvironmentGround(50, 50);
		GUI window = new GUI(env);
	}
}
