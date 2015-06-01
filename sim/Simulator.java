package sim;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import parser.DataToGlobalEnvironment;
import sim.agent.AbstractAgent;
import env2.action.MotionAction;
import env2.api.AbstractAction;
import env2.api.AbstractCell;
import env2.api.InterfaceMother;
import env2.env.GlobalEnvironment;
import env2.influences.CreateBabyInfluence;
import env2.influences.MotionInfluence;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.instanciator.factory.BodyFactory;
import env2.type.WorldObjectType;
import gui.GUI;

public class Simulator {
	
	private final static String DEFAULT_FILENAME = "src/res/conf/environment.txt";
	private static Simulator INSTANCE = null;
	
	/***/
	
	private LinkedList<MotionInfluence> motionInfluences;
	private LinkedList<AbstractAction> actions;
	private HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers;
	private LinkedList<AbstractCell> influencedCells;
	
	/***/
		
	private LinkedList<AbstractAgent> agents;
	private GlobalEnvironment global;
	private GUI gui;
	
	/***/
	
	public static Simulator getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Simulator();
		
		return INSTANCE;
	}
	
	public static Simulator getInstance(String filename) {
		if (INSTANCE == null)
			INSTANCE = new Simulator(filename);
		
		return INSTANCE;
	}
	
	private Simulator() {
		new Simulator(DEFAULT_FILENAME);
	}
	
	private Simulator(String filename) {
		if (filename == null || filename == "" || filename.isEmpty())
			filename = DEFAULT_FILENAME;
		
		DataToGlobalEnvironment instancied = new DataToGlobalEnvironment(filename);
		global = instancied.getGlobalEnvironment(); 
		agents = instancied.getAgents();
		mothers = instancied.getMothers();
		
		gui = new GUI(global.getGrounds());
		gui.setVisible(true);
		
		motionInfluences = new LinkedList<>();
		actions = new LinkedList<>();
		influencedCells = new LinkedList<>();
	}
	
	/***/
	
	public boolean isFinished() {
		return agents == null || agents.isEmpty();
	}
	
	public void doStep() {
		if (agents == null)
			throw new NoSuchElementException("NO AGENT FOUND!");
		
		influencedCells.clear();
		motionInfluences.clear();
		actions.clear();
		mothers.clear();
		
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
		
		createBabies();
	
		/***/
				
		gui.repaint();
	}
	
	/**
	 * For now on, this just creates all motion actions regarding the corresponding influences...
	 * @param motionInfluences List of motion influences to apply
	 * @return List of actions to execute, later
	 * TODO Maybe add some conflict solving with Bresenheim-lines?
	 */
	private LinkedList<MotionAction> solveMotionInfluences(List<MotionInfluence> motionInfluences) {
		LinkedList<MotionAction> actions = new LinkedList<>();
		for (MotionInfluence influence : motionInfluences) {
			actions.add(new MotionAction(influence.mobile, influence.arrivalPos, influence.arrivalEnv));
		}
		return actions;
	}
	
	private void createBabies() {
		boolean token_mother = false;
		int tmp_tribe_id = 0;
		
		for (InterfaceMother mother : mothers.keySet()) {
			
			AbstractBodyInstanciator.POS = mother.getPosition();
			AbstractBodyInstanciator.ENV = mother.getEnvironment();
			AbstractBodyInstanciator.TRIBE_ID = mother.getTribeID();
		
			for (CreateBabyInfluence newBaby : mothers.get(mother)) {
				if (newBaby.type == WorldObjectType.ANTMOTHERBODY || newBaby.type == WorldObjectType.TERMITEMOTHERBODY) {
					tmp_tribe_id = AbstractBodyInstanciator.TRIBE_ID;
					AbstractBodyInstanciator.TRIBE_ID = genereNextTribeID();
					token_mother = true;
				}
				
				BodyFactory.BODY_INSTANCIATOR.get(newBaby.type).getNew();
				AbstractAgent agt = BodyFactory.BODY_INSTANCIATOR.get(newBaby.type).getAgent();
				agents.add(agt);
				
				if (token_mother) {
					AbstractBodyInstanciator.TRIBE_ID = tmp_tribe_id;
					token_mother = false;
				}
			}
		}
	}
	
	/*
	 * To be used when creating new mothers (in exclusivity!!).
	 */
	private int genereNextTribeID() {
		return mothers.size()+1;
	}
}

/* 	// private LinkedList<Class <? extends AbstractAgent>> newAgents;
for (Class<? extends AbstractAgent> c : newAgents) {
	Constructor<? extends AbstractAgent> cons = c.getDeclaredConstructor(Integer.class);
	agents.add(cons.newInstance(1));
} */
