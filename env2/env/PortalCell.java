package env2.env;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.InterfacePortal;

public class PortalCell extends Cell implements InterfacePortal {
	
	private final AbstractEnvironment arrivalEnv;
	private final MyPoint2D arrivalPos;
	
	/* should I allow people to create a portal cell with no portal?
	 * Really...?
	 */
	
	public PortalCell() {
		super();
		arrivalEnv = null;
		arrivalPos = null;
	}
	
	public PortalCell(AbstractEnvironment arrivalEnv, MyPoint2D arrivalPos) {
		super();
		this.arrivalEnv = arrivalEnv;
		this.arrivalPos = arrivalPos;
	}
	
	public boolean isPortal() {
		return arrivalEnv != null;
	}

	public AbstractEnvironment getArrivalEnvironment() {
		return arrivalEnv;
	}

	public MyPoint2D getArrivalPosition() {
		return arrivalPos;
	}
}
