package sim;

import java.io.IOException;
import java.util.LinkedList;

import parser.DataToGlobalEnvironment;
import sim.agent.AbstractAgent;
import env2.env.GlobalEnvironment;
import gui.GUI;

public class Simulator {
	
	public static void main(String[] args) throws IOException {
		
		Simulator sim = new Simulator();
		
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
	
	private LinkedList<AbstractAgent> agents;
	private GlobalEnvironment global;
	private GUI gui;
	
	private final static String DEFAULT_FILENAME = "src/res/conf/environment.txt";
	
	public Simulator() throws IOException {
		new Simulator(DEFAULT_FILENAME);
	}
	
	public Simulator(String filename) throws IOException {
		
		DataToGlobalEnvironment instancied = new DataToGlobalEnvironment(filename);
		global = instancied.getGlobalEnvironment(); 
		agents = instancied.getAgents();
		
		// TODO
		
		gui = new GUI(global.getGrounds()); // TODO @bourgeoismaxime = new GUI(global);
		gui.setVisible(true);
	}
	
	public void doStep() {
		for (AbstractAgent agt : agents) {
			agt.live();
		}
		gui.repaint();
	}
}
