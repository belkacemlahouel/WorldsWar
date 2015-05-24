package sim;

import java.io.IOException;

import parser.DataToGlobalEnvironment;
import sim.agent.AbstractAgent;
import env2.env.GlobalEnvironment;
import gui.GUI;

public class Simulator {
	
	public static void main(String[] args) throws IOException {
		
		Simulator sim = new Simulator();
		System.out.println("Bye world.");
		
		while (true) {
			sim.doStep();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/***/
	
	private AbstractAgent[] agents;
	private GlobalEnvironment global;
	private GUI gui;
	
	private final static String DEFAULT_FILENAME = "res/conf/environment.txt";
	
	public Simulator() throws IOException {
		new Simulator(DEFAULT_FILENAME);
	}
	
	public Simulator(String filename) throws IOException {
		
		global = (new DataToGlobalEnvironment(filename)).getGlobalEnvironment(); 
		
		// TODO
		
		gui = new GUI(global.get(0)); // TODO @bourgeoismaxime = new GUI(global);
		gui.setVisible(true);
	}
	
	public void doStep() {
		for (AbstractAgent agt : agents) {
			agt.live();
		}
		gui.repaint();
	}
}
