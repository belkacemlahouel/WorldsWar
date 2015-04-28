package env2.env;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.InterfacePortal;

public class PortalCell extends Cell implements InterfacePortal {

	@Override
	public boolean isPortal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AbstractEnvironment getArrivalEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyPoint2D getArrivalPosition() {
		// TODO Auto-generated method stub
		return null;
	}

}
