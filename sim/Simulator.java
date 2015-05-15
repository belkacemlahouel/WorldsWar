package sim;

import sim.agent.AbstractAgent;
import env2.env.Environment;
import env2.env.Ground;
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
	
	private AbstractAgent[] agents;
	private Environment envs;
	private GUI gui;
	
	/*
	 * TODO
	 * Insertion of a call to the parser in this constructor...
	 */
	
	public Simulator(int nbGhosts, int width, int height) {
		
		/*
		 * Environments instanciation
		 */
		envs = new Environment(1);
		envs.set(0, new Ground(10, 10));
		
		/*
		 * Agents and their bodies
		 */
		
		/*
		 * Resources put in the environment
		 */
		
		gui = new GUI(envs.get(0));
		gui.setVisible(true);
	}
	
	public void doStep() {
		for (AbstractAgent agt : agents) {
			agt.live();
		}
		gui.repaint();
	}
}
