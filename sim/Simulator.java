package sim;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import math.MyMath;
import math.MyPoint2D;
import parser.DataToGlobalEnvironment;
import sim.agent.AbstractAgent;
import env2.action.MotionAction;
import env2.api.AbstractAction;
import env2.api.AbstractCell;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceMother;
import env2.env.GlobalEnvironment;
import env2.influences.CreateBabyInfluence;
import env2.influences.MotionInfluence;
import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.instanciator.factory.BodyFactory;
import env2.resources.CommonResource;
import env2.type.WorldObjectType;
import gui.GUI;

public class Simulator {
	
	private final static String DEFAULT_FILENAME = "src/res/conf/environment.txt";
	private static Simulator INSTANCE = null;
	
	/***/
	
	/* private static InterfaceGatherer virtualGatherer;
	
	public static InterfaceGatherer getVirtualGatherer() {
		if (virtualGatherer == null)
			virtualGatherer = (InterfaceGatherer) BodyFactory.BODY_INSTANCIATOR.get(WorldObjectType.ANTGATHERERBODY).getNew();
		
		return virtualGatherer;
	} */
	
	private static AbstractCell pointedCell = null;
	
	public static void setPointedCell(AbstractCell cell) {
		pointedCell = cell;
	}
	
	public static void setPointedCell(int env, int i, int j) {
		pointedCell = global.get(env).getCell(i, j);
	}
	
	public static AbstractCell getPointedCell() {
		return pointedCell;
	}
	
	public void addInfluencedByGUICell(AbstractCell c)
	{		
		if(c != null){
			synchronized (influencedByGUICells) {
				this.influencedByGUICells.add(c);	
			}
		}
	}
	
	/***/
	
	private LinkedList<MotionInfluence> motionInfluences;
	private LinkedList<AbstractAction> actions;
	private HashMap<InterfaceMother, List<CreateBabyInfluence>> mothers;
	private LinkedList<AbstractCell> influencedCells;
	private LinkedList<AbstractCell> influencedByGUICells;
	
	/***/
		
	private static LinkedList<AbstractAgent> agents;
	private static GlobalEnvironment global;
	private GUI gui;
	
	// private int nbStep = 0;
	
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
		
		gui = GUI.getInstance();
		gui.setEnvironmentList(global.getGrounds());
		gui.getFrame().setVisible(true);
		
		motionInfluences = new LinkedList<>();
		actions = new LinkedList<>();
		influencedCells = new LinkedList<>();
		influencedByGUICells = new LinkedList<>();
	}
	
	/***/
	
	public boolean isFinished() {
		return agents == null || agents.isEmpty();
	}
		
	public void doStep() {
		
//		System.out.println("" + agents.get(0).getBody().getPosition() + agents.get(0).getBody().getEnvironment());
		
//		if (stp = false) {
//			stp = true;
//			System.out.println("" + agents.get(0).getBody().getPosition() + agents.get(0).getBody().getEnvironment());
//			agents.get(0).getBody().move(global.get(1), new MyPoint2D(2, 2));
//			System.out.println("" + agents.get(0).getBody().getPosition() + agents.get(0).getBody().getEnvironment());
//		} else {
//			stp = false;
//			System.out.println("" + agents.get(0).getBody().getPosition() + agents.get(0).getBody().getEnvironment());
//			agents.get(0).getBody().move(global.get(0), new MyPoint2D(3, 3));
//			System.out.println("" + agents.get(0).getBody().getPosition() + agents.get(0).getBody().getEnvironment());
//		}
		
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
		
		synchronized (influencedByGUICells) {
			this.influencedCells.addAll(this.influencedByGUICells);
			this.influencedByGUICells.clear();			
		}
		
		/***/

		for (AbstractAgent agt : agents) {
			/*List<AbstractWorldObject> list =agt.getBody().getCell().getObjects();
			for(int i=0; i<list.size();++i){
				System.out.println(list.get(i));
			}*/
			//System.out.println(agt.getBody());
			actions.addAll(agt.getBody().solveInfluences());
		}

		for (AbstractCell c : influencedCells) {
			/**----------------------*/
			 /*List<AbstractWorldObject> objs = c.getObjects();
			 for(AbstractWorldObject o : objs){
				 if(WorldObjectType.canBeFood( o.getType())){
					 System.out.println(o.getType()+" : " +((CommonResource) o).getQuantity());
				 }
			 }*/
			 /**--------------------*/
			
			actions.addAll(c.solveInfluences());
		}
		
		actions.addAll(solveMotionInfluences(motionInfluences));
		
		/***/

		for (AbstractAction action : actions) {
			action.doAction();
		}
		
		/***/
		
		for (AbstractAgent agt : agents) {
			agt.getBody().applyLifeVariation(-agt.getBody().getLifeLoss());
		}

		/***/
		
		createBabies();
	
		/***/	
		/*nbStep++;
		if(nbStep%10 == 0)
	 		System.out.println(agents.size());*/
		
		gui.refresh(true);
	}
	
	/**
	 * For now on, this just creates all motion actions regarding the corresponding influences...
	 * @param motionInfluences List of motion influences to apply
	 * @return List of actions to execute, later
	 * TODO Maybe add some conflict solving with Bresenheim-lines?
	 * TODO Check if the guy want to move in its reach?
	 */
	private LinkedList<MotionAction> solveMotionInfluences(List<MotionInfluence> motionInfluences) {
		LinkedList<MotionAction> actions = new LinkedList<>();
		for (MotionInfluence influence : motionInfluences) {
			if (MyMath.isIn(influence.arrivalPos.getX(), influence.arrivalPos.getY(),
					influence.arrivalEnv.getWidth(), influence.arrivalEnv.getHeight())) {
				actions.add(new MotionAction(influence.mobile, influence.arrivalPos, influence.arrivalEnv));
			} else {
				int tmpX, tmpY;
				tmpX = Math.max(0, Math.min(influence.arrivalPos.getX(), influence.arrivalEnv.getWidth()));
				tmpY = Math.max(0, Math.min(influence.arrivalPos.getY(), influence.arrivalEnv.getHeight()));
				actions.add(new MotionAction(influence.mobile, new MyPoint2D(tmpX, tmpY), influence.arrivalEnv));
			}
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
