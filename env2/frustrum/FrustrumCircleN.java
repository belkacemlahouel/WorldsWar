package env2.frustrum;

import java.util.Iterator;
import java.util.Stack;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;

/**
 * We name it "circle frustrum" but it works on a discrete environment.
 * It's more of a square actually.
 * @author belka
 *
 */

public class FrustrumCircleN extends AbstractFrustrum {

	protected final int radius;
	protected Iterator<Perception> objects;
	
	public FrustrumCircleN(AbstractBody b, AbstractEnvironment e, int radius) {
		super(b, e);
		this.radius = radius;
		objects = new CircleIteratorN(b.getPosition());
	}
	
	public FrustrumCircleN(AbstractBody b, AbstractEnvironment e) {
		super(b, e);
		radius = 1;
		objects = new CircleIteratorN(b.getPosition());
	}
	
	public Iterator<Perception> objects() {
		return objects;
	}
	
	private class CircleIteratorN implements Iterator<Perception> {
		
		private int x, y;	// iteration variables, properly initialized
		private int bx; 	// beginning for x iteration variable, to reinitialize x when needed
		private int ex, ey;	// limit for iteration variables
		private int mx, my;	// body position...
		private Stack<Perception> next;
		
		public CircleIteratorN(MyPoint2D pos) {
			mx = pos.getX();
			my = pos.getY();
			
			bx = Math.min(Math.max(0, mx-radius), e.getWidth()-1);
			x = bx;
			y = Math.min(Math.max(0, my-radius), e.getHeight()-1);
			ex = Math.min(Math.max(0, mx+radius), e.getWidth()-1);
			ey = Math.min(Math.max(0, my+radius), e.getHeight()-1);
			
			next = new Stack<Perception>();
			searchNexts();
		}

		public Perception next() {
			if (next.isEmpty())
				searchNexts();
			
			return next.pop();
		}
		
		/***/
		
		public boolean hasNext() {
			return !next.isEmpty() && x <= ex && y <= ey;
		}
		
		private void searchNexts() {
			while (next.isEmpty() && x <= ex && y <= ey) {
				for (AbstractWorldObject obj : e.getCell(x, y).getObjects()) {
					if (b != obj)
						next.add(new Perception(obj, new MyPoint2D(x, y)));
				}
				
				++x;
				if (x > ex) {
					x = bx;
					++y;
				}
			}
		}

		public void remove() {
			return;
		}
	}
}
