package sim;

import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import parser.DataToGlobalEnvironment;
import sim.agent.AbstractAgent;
import env2.api.AbstractAction;
import env2.env.GlobalEnvironment;
import gui.GUI;

public class Simulator {
	
	private final static String DEFAULT_FILENAME = "src/res/conf/environment.txt";
	
	/***/
	
	// private LinkedList<MotionInfluence> motionInfluences;
	private LinkedList<AbstractAction> actions;
	
	/***/
	
	private LinkedList<AbstractAgent> agents;
	private GlobalEnvironment global;
	private GUI gui;
	
	/***/
	
	public Simulator() throws IOException {
		new Simulator(DEFAULT_FILENAME);
	}
	
	public Simulator(String filename) throws IOException {
		
		DataToGlobalEnvironment instancied = new DataToGlobalEnvironment(filename);
		global = instancied.getGlobalEnvironment(); 
		agents = instancied.getAgents();
		
		gui = new GUI(global.getGrounds());
		gui.setVisible(true);
		
		// motionInfluences = new LinkedList<>();
		actions = new LinkedList<>();
	}
	
	/***/
	
	public void doStep() {
		if (agents == null)
			throw new NoSuchElementException("NO AGENT FOUND!");
		
		// motionInfluences.clear();
		actions.clear();
		
		for (AbstractAgent agt : agents) {
			// TODO
			// MotionInfluence influence = agt.live();
			// if (influence != null)
			// 		motionInfluences.add(influence);
		}
		
		for (AbstractAgent agt : agents) {
			// TODO
			// actions.addAll(agt.getBody().solveInfluences());
		}
		
		// TODO
		// actions.addAll(solveMotionInfluences(motionInfluences));
		
		for (AbstractAction action : actions) {
			action.doAction();
		}
		
		gui.repaint();
	}
}
