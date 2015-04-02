package env;

import java.util.Iterator;

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

class FrustrumCircle extends Frustrum {
	
	private Iterator<WorldObject> objects;
	
	public FrustrumCircle(Body b, Environment e) {
		super(b, e);
		objects = new CircleIterator(b.getPosition());
	}
	
	public Iterator<WorldObject> objects() {
		return objects; 
	}
	
	private class CircleIterator implements Iterator<WorldObject> {
		
		private int x, y;
		private int ex, ey;
		private int mx, my;
		
		public CircleIterator(MyPoint2D pos) {
			mx = (int) pos.getX();
			my = (int) pos.getY();
			x = Math.min(Math.max(0, (int) pos.getX()-1), e.getWidth()-1);
			y = Math.min(Math.max(0, (int) pos.getY()-1), e.getHeight()-1);
			ex = Math.min(e.getWidth()-1, x+2);
			ey = Math.min(e.getHeight()-1, y+2);
			System.out.println("Frustrum l45: " + x + ", " + y + " | " + ex + ", " + ey + " | " + mx + ", " + my);
		}

		public boolean hasNext() {
			return x <= ex && y <= ey;
		}

		public WorldObject next() {
			WorldObject ans = null;
			do {
				ans = e.getCell(x, y).getObject(); 
				searchNext();
			} while (hasNext() && ans == null);
			
			return ans;
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
