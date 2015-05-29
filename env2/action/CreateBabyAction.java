package env2.action;

import java.util.LinkedList;

import sim.agent.AbstractAgent;
import env2.api.AbstractAction;
import env2.api.InterfaceMother;

public class CreateBabyAction extends AbstractAction {

	private InterfaceMother mother;
	public LinkedList<AbstractAgent> newAgents;
	public int qty;
	
	public CreateBabyAction(InterfaceMother mother, LinkedList<AbstractAgent> newAgents, int qty) {
		this.mother = mother;
		this.newAgents = newAgents;
		this.qty = qty;
	}
	
	public void doAction() {
		// TODO
		// mother.addBaby();
	}
}
