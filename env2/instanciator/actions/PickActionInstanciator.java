package env2.instanciator.actions;

import env2.action.PickAction;
import env2.influences.PickInfluence;

public class PickActionInstanciator extends AbstractActionInstanciator {

	@Override
	public PickAction getAction() {
		PickInfluence infl = (PickInfluence) influence;
		return new PickAction(infl.picker, infl.container, infl.picked, infl.qty);
	}
}
