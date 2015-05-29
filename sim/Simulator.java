package sim;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import parser.DataToGlobalEnvironment;
import sim.agent.AbstractAgent;
import env2.action.MotionAction;
import env2.action.influences.CreateBabyInfluence;
import env2.action.influences.MotionInfluence;
import env2.api.AbstractAction;
import env2.api.AbstractCell;
import env2.api.InterfaceMother;
import env2.env.GlobalEnvironment;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.instanciator.factory.BodyFactory;
import gui.GUI;

public class Simulator {
	
	private final static String DEFAULT_FILENAME = "src/res/conf/environment.txt";
	
	/***/
	
	private LinkedList<MotionInfluence> motionInfluences;
	private LinkedList<AbstractAction> actions;
	private HashMap<InterfaceMother, LinkedList<CreateBabyInfluence>> newAgents;
	private LinkedList<AbstractCell> influencedCells;
	
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
		
		motionInfluences = new LinkedList<>();
		actions = new LinkedList<>();
		newAgents = new HashMap<>();
	}
	
	/***/
	
	public void doStep() {
		if (agents == null)
			throw new NoSuchElementException("NO AGENT FOUND!");
		
		influencedCells.clear();
		motionInfluences.clear();
		actions.clear();
		newAgents.clear();
		
		for (AbstractAgent agt : agents) {
			MotionInfluence influence = agt.live();
			if (influence != null)
				motionInfluences.add(influence);
			
			// TODO Check that all agents act then move <=> They act in this current cell
			influencedCells.add(agt.getBody().getEnvironment().getCell(agt.getBody().getPosition()));
		}
		
		/***/
		
		for (AbstractAgent agt : agents) {
			actions.addAll(agt.getBody().solveInfluences());
		}
		
		for (AbstractCell c : influencedCells) {
			actions.addAll(c.solveInfluences());
		}
		
		actions.addAll(solveMotionInfluences(motionInfluences));
		
		/***/
		
		for (AbstractAction action : actions) {
			action.doAction();
		}
		
		for (InterfaceMother mother : newAgents.keySet()) {
			
			AbstractBodyInstanciator.POS = mother.getPosition();
			AbstractBodyInstanciator.ENV = mother.getEnvironment();
			AbstractBodyInstanciator.TRIBE_ID = mother.getTribeID();
		
			for (CreateBabyInfluence newBaby : newAgents.get(mother)) {
				BodyFactory.BODY_INSTANCIATOR.get(newBaby.type).getNew();
				AbstractAgent agt = BodyFactory.BODY_INSTANCIATOR.get(newBaby.type).getAgent();
				agents.add(agt);
			}
		}
	
		/***/
		
		/* 	// private LinkedList<Class <? extends AbstractAgent>> newAgents;
			for (Class<? extends AbstractAgent> c : newAgents) {
			Constructor<? extends AbstractAgent> cons = c.getDeclaredConstructor(Integer.class);
			agents.add(cons.newInstance(1));
		} */
		
		gui.repaint();
	}
	
	private List<MotionAction> solveMotionInfluences(List<MotionInfluence> motionInfluences) {
		// TODO
		return null;
	}
}
