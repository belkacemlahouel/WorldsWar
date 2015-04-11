package sim;

import env.environment.Environment;
import gui.GUI;

public class Simulator {
	
	public static void main(String[] args) {
		
		final int NB_GHOSTS = 2, WIDTH = 10, HEIGHT = 4;
		int step = 0;
		
		Simulator sim = new Simulator(NB_GHOSTS, WIDTH, HEIGHT);
		System.out.println("Bye world.");
		
		while (true) {
			System.out.println("Step# " + (step++));
			sim.doStep();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/***/
	
	private Agent[] agents;
	private Environment env;
	private GUI gui;
	
	public Simulator(int nbGhosts, int width, int height) {
		// env = new Environment(width, height); // TODO Find a solution to instantiate the Environment
		
		// TODO Fill this main up
		
		gui = new GUI(env);
		gui.setVisible(true);
	}
	
	public void doStep() {
		for (Agent agt : agents) {
			agt.live();
		}
		gui.repaint();
	}
}
