package env2.frustrum.old;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;

public class OldFrustrumCircleN extends OldAbstractFrustrum {
	
	protected final int radius;
	protected Iterator<AbstractWorldObject> objects;
	
	public OldFrustrumCircleN(AbstractBody b, AbstractEnvironment e, int radius) {
		super(b, e);
		this.radius = radius;
		objects = new CircleIteratorN(b.getPosition());
	}
	
	public OldFrustrumCircleN(AbstractBody b, AbstractEnvironment e) {
		super(b, e);
		radius = 1;
		objects = new CircleIteratorN(b.getPosition());
	}
	
	public Iterator<AbstractWorldObject> objects() {
		return objects;
	}
	
	private class CircleIteratorN implements Iterator<AbstractWorldObject> {
		
		private int x, y;
		private int ex, ey;
		private int mx, my;
		private List<AbstractWorldObject> next;
		
		public CircleIteratorN(MyPoint2D pos) {
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
			if (next.isEmpty()) { // FIXME 1 or 0 elements?
				do {
					next.addAll(e.getCell(x, y).getObjects()); 
					searchNext();
				} while (hasNext() && next.isEmpty());
			} else {
				next.remove(0);
			}
			
			return next.get(0);
		}
		
		public void searchNext() {
			do {
				++x;
				if (x > ex) {
					x -= 2*radius+1;
					++y;
				}
			} while (x == mx && y == my);
		}

		public void remove() {
			return;
		}
	}
}
