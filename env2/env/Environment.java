package env2.env;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractWorldObject;
import env2.api.Morphable;
import env2.body.ClassicBody;

public class Environment {

	//public AbstractEnvironment[] grounds;
	
	private List<AbstractEnvironment> grounds;
	private List<AbstractBody> bodies;
	private List<Morphable> morph;
	
	public Environment(){
		grounds = new ArrayList<AbstractEnvironment>();
		grounds.add(new Ground(10, 10));
		this.bodies = new ArrayList<AbstractBody>();
		this.morph = new ArrayList<Morphable>();
	}
	
	public void go(){
		boolean bool = true;
		Iterator <Morphable> it;
		Morphable m;
		while(bool){
			
			for(AbstractBody b : bodies){
				bool = b.move(b.getEnvironment(), new MyPoint2D(b.getPosition().getX() + 1, b.getPosition().getY()) );
				System.out.println("x = " + b.getPosition().getX() + " y = " + b.getPosition().getY());
			}
			
			it = morph.iterator();
			while(it.hasNext()){
				m = it.next();
				if (m.tic()){
					it.remove();
					this.remove((AbstractWorldObject) m);
				}
				System.out.println(m.getCount());
			}
			
		}
	}
	
	public boolean add (AbstractWorldObject o){
		
		AbstractEnvironment e = o.getEnvironment();
		MyPoint2D pos = o.getPosition();
		if( e != null && this.grounds.contains(e)){
			if(pos.getX() >= 0 && pos.getX() < e.getWidth() && pos.getY() >= 0 && pos.getY() < e.getHeight() ){
				
				e.getCell(pos.getX(), pos.getY()).addObject(o);
				
				if(o instanceof AbstractBody){
					this.bodies.add((AbstractBody) o);
				}
					
				if(o instanceof Morphable){

					this.morph.add((Morphable)o);
				}
				
				return true;
			}
		}
		return false;
		
	}
	
	public boolean remove (AbstractWorldObject o){
		AbstractEnvironment e = o.getEnvironment();
		MyPoint2D pos = o.getPosition();
		System.out.println(e!=null);
		if( e != null && this.grounds.contains(e)){
			if(pos.getX() >= 0 && pos.getX() < e.getWidth() && pos.getY() >= 0 && pos.getY() < e.getHeight() ){
				if (e.getCell(pos.getX(), pos.getY()).removeObject(o)){
					if(o instanceof AbstractBody){
						this.bodies.remove((AbstractBody) o);
					}
						
					
					
					return true;
				}
			}
		}
		return false;
	}
	
	public AbstractEnvironment getEnvironment(int i){
		return this.grounds.get(i);
	}
}
