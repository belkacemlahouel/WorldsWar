package env2.body;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractFrustrum;
import env2.frustrum.FrustrumCircleN;
import env2.frustrum.FrustrumCrossN;
import env2.type.Direction;
import env2.type.FrustrumType;

public abstract class ClassicBody extends AbstractBody {
	
	private final float CREATION_TIME;
	protected final int TRIBE_ID = 0;

	private AbstractFrustrum myfrustrum;
	private AbstractEnvironment myenv;
	private Direction mydir;
	private MyPoint2D mypos;
	protected int mylife;
	
	public ClassicBody(AbstractEnvironment e, Direction dir, MyPoint2D pos, float TIME) {
		CREATION_TIME = TIME;
		
		myenv = e;
		mydir = dir;
		mypos = pos;
		myfrustrum = null;
		mylife = getTotMaxLife();
	}
	
	/***/
	
	public void buildNewFrustrum(FrustrumType type) {
		switch(type) {
			case CROSS:
				myfrustrum = new FrustrumCrossN(this, myenv, getReach());
				break;
				
			default:
				myfrustrum = new FrustrumCircleN(this, myenv, getReach());
		}
	}

	public AbstractFrustrum getCurrentFrustrum() {
		return myfrustrum;
	}

	public AbstractEnvironment getEnvironment() {
		return myenv;
	}

	public MyPoint2D getPosition() {
		return mypos;
	}

	public Direction getDirection() {
		return mydir;
	}
	
	/***/

	public int getCurrentLife() {
		return mylife;
	}
	
	public boolean isFriend(AbstractBody b) {
		return b.getTribeID() == getTribeID();
	}

	public boolean isPerceivable() {
		return true;
	}
	
	/***/
	
	/* this method only takes care of moving the body to the arrival case
	 * nothing else is done here
	 * (influences solving/distance checking etc)
	 * hence, you should not forget to erase the current frustrum
	 * 
	 * (non-Javadoc)
	 * @see env2.api.AbstractMobileWorldObject#move(env2.api.AbstractCell)
	 */
	public void move(AbstractEnvironment newenv, MyPoint2D newpos) {
		newenv.getCell(newpos.getX(), newpos.getY()).addObject(this);
		myenv.getCell(mypos.getX(), mypos.getY()).removeObject(this);
	}
	
	public int getAge(float TIME) {
		return (int) (TIME - CREATION_TIME);
	}
}
