package env2.frustrum;

import java.util.Iterator;
import java.util.Stack;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;

public class FrustrumCrossN2 extends AbstractFrustrum2 {

	protected final int length;
	protected Iterator<AbstractWorldObjectWithPosition> objects;
	
	public FrustrumCrossN2(AbstractBody b, AbstractEnvironment e, int length) {
		super(b, e);
		this.length = length;
		objects = new CrossIteratorN(b.getPosition());
	}
	
	public FrustrumCrossN2(AbstractBody b, AbstractEnvironment e) {
		super(b, e);
		length = 1;
		objects = new CrossIteratorN(b.getPosition());
	}
	
	public Iterator<AbstractWorldObjectWithPosition> objects() {
		return objects;
	}
	
	private class CrossIteratorN implements Iterator<AbstractWorldObjectWithPosition> {
		
		private int x, y;	// iteration variables, properly initialized
		private int ex, ey;	// limit for iteration variables
		private int mx, my;	// body position...
		private Stack<AbstractWorldObjectWithPosition> next;
		
		/***/
		
		public CrossIteratorN(MyPoint2D pos) {
			mx = pos.getX();
			my = pos.getY();
			
			x = Math.min(Math.max(0, mx-length), e.getWidth()-1);
			y = Math.min(Math.max(0, my-length), e.getHeight()-1);
			ex = Math.min(Math.max(0, mx+length), e.getWidth()-1);
			ey = Math.min(Math.max(0, my+length), e.getHeight()-1);
			
			next = new Stack<AbstractWorldObjectWithPosition>();
		}
		
		public AbstractWorldObjectWithPosition next() {
			
			while (next.isEmpty() && hasNext()) {
				if (x < ex) {
					for (AbstractWorldObject obj : e.getCell(x, my).getObjects()) {
						if (b != obj)
							next.add(new AbstractWorldObjectWithPosition(obj, new MyPoint2D(x, my)));
					}
				} else {
					for (AbstractWorldObject obj : e.getCell(x, my).getObjects()) {
						if (b != obj)
							next.add(new AbstractWorldObjectWithPosition(obj, new MyPoint2D(mx, y)));
					}
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
			if (x < ex) ++x;
			else if (x > ex) ++y;
		}

		public void remove() {
			return;
		}
	}
}
