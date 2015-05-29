package env2.influences;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractInfluence;
import env2.api.AbstractMobileWorldObject;
import env2.type.InfluenceType;

public class MotionInfluence extends AbstractInfluence {

	public final AbstractMobileWorldObject mobile;
	public final MyPoint2D arrivalPos;
	public final AbstractEnvironment arrivalEnv;
	
	public MotionInfluence(AbstractMobileWorldObject mobile, MyPoint2D arrivalPos, AbstractEnvironment arrivalEnv) {
		this.mobile = mobile;
		this.arrivalPos = arrivalPos;
		this.arrivalEnv = arrivalEnv;
	}

	@Override
	public InfluenceType getType() {
		return InfluenceType.MOTION;
	}
}
