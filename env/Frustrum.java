package env;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import math.MyPoint2D;

public abstract class Frustrum {
	
	protected Body b;
	protected Environment e;
	
	public Frustrum(Body b, Environment e) {
		this.b = b;
		this.e = e;
	}

	public abstract Iterator<WorldObject> objects();
}

class FrustrumCircleN extends Frustrum {
	
	protected final int radius;
	protected Iterator<WorldObject> objects;
	
	public FrustrumCircleN(Body b, Environment e, int radius) {
		super(b, e);
		this.radius = radius;
		objects = new CircleIteratorN(b.getPosition());
	}
	
	public FrustrumCircleN(Body b, Environment e) {
		super(b, e);
		radius = 1;
		objects = new CircleIteratorN(b.getPosition());
	}
	
	public Iterator<WorldObject> objects() {
		return objects;
	}
	
	private class CircleIteratorN implements Iterator<WorldObject> {
		
		private int x, y;
		private int ex, ey;
		private int mx, my;
		private List<WorldObject> next;
		
		public CircleIteratorN(MyPoint2D pos) {
			mx = pos.getX();
			my = pos.getY();
			
			x = Math.min(Math.max(0, pos.getX()-radius), e.getWidth()-1);
			y = Math.min(Math.max(0, pos.getY()-radius), e.getHeight()-1);
			ex = Math.min(e.getWidth()-1, x+2*radius);
			ey = Math.min(e.getHeight()-1, y+2*radius);
			
			next = new ArrayList<WorldObject>();
		}

		public boolean hasNext() {
			return x <= ex && y <= ey;
		}

		public WorldObject next() {
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

class FrustrumCrossN extends Frustrum {
	
	protected final int radius;
	protected Iterator<WorldObject> objects;
	
	public FrustrumCrossN(Body b, Environment e, int radius) {
		super(b, e);
		this.radius = radius;
		objects = new CrossIteratorN(b.getPosition());
	}
	
	public FrustrumCrossN(Body b, Environment e) {
		super(b, e);
		radius = 1;
		objects = new CrossIteratorN(b.getPosition());
	}
	
	public Iterator<WorldObject> objects() {
		return objects;
	}
	
	private class CrossIteratorN implements Iterator<WorldObject> {
		
		private int x, y;
		private int ex, ey;
		private int mx, my;
		private List<WorldObject> next;
		
		public CrossIteratorN(MyPoint2D pos) {
			mx = pos.getX();
			my = pos.getY();
			
			x = Math.min(Math.max(0, pos.getX()-radius), e.getWidth()-1);
			y = Math.min(Math.max(0, pos.getY()-radius), e.getHeight()-1);
			ex = Math.min(e.getWidth()-1, x+2*radius);
			ey = Math.min(e.getHeight()-1, y+2*radius);
			
			next = new ArrayList<WorldObject>();
		}

		public boolean hasNext() {
			return x <= ex && y <= ey;
		}

		public WorldObject next() {
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
