package env2.instanciator.actions;

import env2.action.MotionAction;
import env2.influences.MotionInfluence;

public class MotionActionInstanciator extends AbstractActionInstanciator {

	@Override
	public MotionAction getAction() {
		MotionInfluence infl = (MotionInfluence) influence;
		return new MotionAction(infl.mobile, infl.arrivalPos, infl.arrivalEnv);
	}
}
