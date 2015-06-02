package env2.instanciator.actions;

import env2.action.CreateBabyAction;
import env2.influences.CreateBabyInfluence;

public class CreateBabyActionInstanciator extends AbstractActionInstanciator {

	@Override
	public CreateBabyAction getAction() {
		CreateBabyInfluence infl = (CreateBabyInfluence) influence;
		return new CreateBabyAction(infl.mother);
	}
}
