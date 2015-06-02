package env2.frustrum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;
import env2.frustrum.old.AbstractFrustrum;

public class FrustrumCrossN extends AbstractFrustrum {
	
	protected final int radius;
	protected Iterator<AbstractWorldObject> objects;
	
	public FrustrumCrossN(AbstractBody b, AbstractEnvironment e, int radius) {
		super(b, e);
		this.radius = radius;
		objects = new CrossIteratorN(b.getPosition());
	}
	
	public FrustrumCrossN(AbstractBody b, AbstractEnvironment e) {
		super(b, e);
		radius = 1;
		objects = new CrossIteratorN(b.getPosition());
	}
	
	public Iterator<AbstractWorldObject> objects() {
		return objects;
	}
	
	private class CrossIteratorN implements Iterator<AbstractWorldObject> {
		
		private int x, y;
		private int ex, ey;
		private int mx, my;
		private List<AbstractWorldObject> next;
		
		public CrossIteratorN(MyPoint2D pos) {
			mx = pos.getX();
			my = pos.getY();
			
			x = Math.min(Math.max(0, pos.getX()-radius), e.getWidth()-1);
			y = Math.min(Math.max(0, pos.getY()-radius), e.getHeight()-1);
			ex = Math.min(e.getWidth()-1, x+2*radius);
			ey = Math.min(e.getHeight()-1, y+2*radius);
			
			next = new ArrayList<AbstractWorldObject>();
		}

		public boolean hasNext() {
			return x <= ex && y <= ey;
		}

		public AbstractWorldObject next() {
			if (next.size() < 2) { // FIXME at 1 or 0 elements we should search again? Because of return get(0)
				do {
					if (x < ex) next.addAll(e.getCell(x, my).getObjects()); // FIXME Check this computation
					else next.addAll(e.getCell(mx, y).getObjects());
					searchNext();
				} while (hasNext() && next.isEmpty()); // FIXME do {} while or while?
			} else {
				next.remove(0);
			}
			
			return next.get(0);
		}
		
		public void searchNext() {
			do {
				if (x < ex) ++x;
				else if (x > ex) ++y;
			} while (x == mx && y == my);
		}

		public void remove() {
			return;
		}
	}
}
