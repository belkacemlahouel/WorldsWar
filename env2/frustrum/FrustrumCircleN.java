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
	protected Iterator<AbstractWorldObjectWithPosition> objects;
	
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
	
	public Iterator<AbstractWorldObjectWithPosition> objects() {
		return objects;
	}
	
	private class CircleIteratorN implements Iterator<AbstractWorldObjectWithPosition> {
		
		private int x, y;	// iteration variables, properly initialized
		private int bx; 	// beginning for x iteration variable, to reinitialize it
		private int ex, ey;	// limit for iteration variables
		private int mx, my;	// body position...
		private Stack<AbstractWorldObjectWithPosition> next;
		
		public CircleIteratorN(MyPoint2D pos) {
			mx = pos.getX();
			my = pos.getY();
			
			bx = Math.min(Math.max(0, mx-radius), e.getWidth()-1);
			x = bx;
			y = Math.min(Math.max(0, my-radius), e.getHeight()-1);
			ex = Math.min(Math.max(0, mx+radius), e.getWidth()-1);
			ey = Math.min(Math.max(0, my+radius), e.getHeight()-1);
			
			next = new Stack<AbstractWorldObjectWithPosition>();
		}

		public AbstractWorldObjectWithPosition next() {
			while (next.isEmpty() && hasNext()) {
				for (AbstractWorldObject obj : e.getCell(x, my).getObjects()) {
					if (b != obj)
						next.add(new AbstractWorldObjectWithPosition(obj, new MyPoint2D(x, y)));
				}
				
				searchNext();
			}
			
			return next.pop();
		}
		
		/***/
		
		public boolean hasNext() {
			return x <= ex && y <= ey;
		}
		
		private void searchNext() {
			++x;
			if (x > ex) {
				x = bx;
				++y;
			}
		}

		public void remove() {
			return;
		}
	}
}
