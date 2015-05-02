package env2.action;

import math.MyPoint2D;
import env2.api.AbstractAction;
import env2.api.AbstractEnvironment;
import env2.api.AbstractMobileWorldObject;

public class MotionAction extends AbstractAction {

	private final AbstractMobileWorldObject mobile;
	private final MyPoint2D arrivalPos;
	private final AbstractEnvironment arrivalEnv;
	
	public MotionAction(AbstractMobileWorldObject mobile, MyPoint2D arrivalPos, AbstractEnvironment arrivalEnv) {
		this.mobile = mobile;
		this.arrivalPos = arrivalPos;
		this.arrivalEnv = arrivalEnv;
	}
	
	public void doAction() {
		mobile.move(arrivalEnv, arrivalPos);
	}
}
