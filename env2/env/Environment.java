package env2.env;

import java.util.ArrayList;
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
		boolean bool = true;;
		while(bool){
			for(AbstractBody b : bodies){
				bool = b.move(b.getEnvironment(), new MyPoint2D(b.getPosition().getX() + 1, b.getPosition().getY()) );
				System.out.println("x = " + b.getPosition().getX() + " y = " + b.getPosition().getY());
			}
			
			for(Morphable m : morph){
				m.tic();
				System.out.println(m.getCount());
			}
		}
	}
	
	public boolean add (AbstractWorldObject o){
		
		AbstractEnvironment e = o.getEnvironment();
		MyPoint2D pos = o.getPosition();
		System.out.println(e!=null);
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
	
	//public boolean remove 
	
	public AbstractEnvironment getEnvironment(int i){
		return this.grounds.get(i);
	}
}
