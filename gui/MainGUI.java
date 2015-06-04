package gui;

import java.util.ArrayList;
import java.util.List;

import env2.api.AbstractEnvironment;
import env2.env.Ground;

public class MainGUI {

	public static void main(String[] args) {		
		List<AbstractEnvironment> envList = new ArrayList<AbstractEnvironment>();
		envList.add(new Ground(60, 60));
		
		envList.add(new Ground(60, 60));
		envList.add(new Ground(60, 60));

		envList.add(new Ground(60, 60));
		envList.add(new Ground(60, 60));
		
		GUI window = GUI.getInstance();
		window.setEnvironmentList(envList, 0);
	}
}
