package env2.env;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.Morphable;
import env2.pheromones.AbstractPheromone;

public class Environment {

	private List<AbstractEnvironment> grounds;
	private List<AbstractBody> bodies;
	private List<Morphable> morph;
	
	public Environment() {
		grounds = new ArrayList<AbstractEnvironment>();
		grounds.add(new Ground(10, 10));
		this.bodies = new ArrayList<AbstractBody>();
		this.morph = new ArrayList<Morphable>();
	}
	
	public void update() {
		boolean bool = true;
		Iterator <Morphable> it;
		Morphable m;
		
		while (bool) {
			for(AbstractBody b : bodies) {
				bool = b.move(b.getEnvironment(), new MyPoint2D(b.getPosition().getX() + 1, b.getPosition().getY()));
				System.err.println("x = " + b.getPosition().getX() + " y = " + b.getPosition().getY());
			}
			
			it = morph.iterator();
			while (it.hasNext()) {
				m = it.next();
				if (m.tic()) {
					it.remove();
					// this.remove((AbstractWorldObject) m);
				}
				
				System.err.println(m.getCount());
			}
		}
	}
	
	/* I don't think we are going to use this method this doesn't seem really needed
	 * the environment does not decide to remove or add an object
	 * except when the WO is a pheromone, and in this case
	 */
	
	/* public boolean add (AbstractWorldObject o) {
		AbstractEnvironment e = o.getEnvironment();
		MyPoint2D pos = o.getPosition();
		
		if (e != null && this.grounds.contains(e)) {
			if (pos.getX() >= 0 && pos.getX() < e.getWidth() && pos.getY() >= 0 && pos.getY() < e.getHeight()) {
				e.getCell(pos.getX(), pos.getY()).addObject(o);
				
				if (o instanceof AbstractBody) {
					this.bodies.add((AbstractBody) o);
				}
					
				if (o instanceof Morphable) {
					this.morph.add((Morphable)o);
				}
				
				return true;
			}
		}
		
		return false;
	} */
	
	public boolean remove(AbstractPheromone o) {
		AbstractEnvironment e = o.getEnvironment();
		MyPoint2D pos = o.getPosition();
		System.err.println(e != null);
		
		if (e != null && this.grounds.contains(e) && MyMath.isIn(pos.getX(), pos.getY(), e.getWidth(), e.getHeight())) {
			return (e.getCell(pos.getX(), pos.getY()).removeObject(o));
		}
		
		return false;
	}
	
	public AbstractEnvironment getEnvironment(int i) {
		if (i < grounds.size())
			return this.grounds.get(i);
		return null;
	}
}
