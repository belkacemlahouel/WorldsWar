package env2.action;

import env2.api.AbstractAction;
import env2.api.InterfaceMother;

public class CreateBabyAction extends AbstractAction {

	private InterfaceMother mother;
	
	public CreateBabyAction(InterfaceMother mother) {
		this.mother = mother;
	}
	
	public void doAction() {
		// TODO This is not enough!!
		mother.addBaby();
	}
}
