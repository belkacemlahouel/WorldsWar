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

class FrustrumCircleOne extends Frustrum {
	
	protected Iterator<WorldObject> objects;
	
	public FrustrumCircleOne(Body b, Environment e) {
		super(b, e);
		objects = new CircleIteratorOne(b.getPosition());
	}
	
	public Iterator<WorldObject> objects() {
		return objects; 
	}
	
	private class CircleIteratorOne implements Iterator<WorldObject> {
		
		private int x, y;
		private int ex, ey;
		private int mx, my;
		private List<WorldObject> next;
		
		public CircleIteratorOne(MyPoint2D pos) {
			mx = pos.getX();
			my = pos.getY();
			
			x = Math.min(Math.max(0, pos.getX()-1), e.getWidth()-1);
			y = Math.min(Math.max(0, pos.getY()-1), e.getHeight()-1);
			ex = Math.min(e.getWidth()-1, x+2);
			ey = Math.min(e.getHeight()-1, y+2);
			
			next = new ArrayList<WorldObject>();
		}

		public boolean hasNext() {
			return x <= ex && y <= ey;
		}

		public WorldObject next() {
			if (next.isEmpty()) {
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
					x -= 3;
					++y;
				}
			} while (x == mx && y == my);
		}

		public void remove() {
			return;
		}
	}
}

class FrustrumCircleN extends FrustrumCircleOne {
	
	private final int radius;
	
	public FrustrumCircleN(Body b, Environment e, int radius) {
		super(b, e);
		this.radius = radius;
		objects = new CircleIteratorN(b.getPosition());
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
			if (next.isEmpty()) {
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