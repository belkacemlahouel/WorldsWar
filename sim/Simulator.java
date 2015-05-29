package sim;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import parser.DataToGlobalEnvironment;
import sim.agent.AbstractAgent;
import env2.action.MotionAction;
import env2.action.influences.MotionInfluence;
import env2.api.AbstractAction;
import env2.api.AbstractCell;
import env2.env.Cell;
import env2.env.GlobalEnvironment;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.instanciator.bodies.ant.AntGathererInstanciator;
import env2.instanciator.bodies.ant.AntMotherInstanciator;
import env2.instanciator.bodies.ant.AntNurseInstanciator;
import env2.instanciator.bodies.ant.AntSoldierInstanciator;
import env2.instanciator.bodies.ant.AntUndertakerInstanciator;
import env2.instanciator.bodies.spider.SpiderInstanciator;
import env2.instanciator.bodies.termite.TermiteGathererInstanciator;
import env2.instanciator.bodies.termite.TermiteMotherInstanciator;
import env2.instanciator.bodies.termite.TermiteNurseInstanciator;
import env2.instanciator.bodies.termite.TermiteSoldierInstanciator;
import env2.instanciator.bodies.termite.TermiteUndertakerInstanciator;
import env2.type.WorldObjectType;
import gui.GUI;

public class Simulator {
	
	private final static String DEFAULT_FILENAME = "src/res/conf/environment.txt";
	
	/***/
	
	private LinkedList<MotionInfluence> motionInfluences;
	private LinkedList<AbstractAction> actions;
	private LinkedList<Class <? extends AbstractAgent>> newAgents;
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
		newAgents = new LinkedList<>();
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
		
		/***/
		
		/* for (Class<? extends AbstractAgent> c : newAgents) {
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
