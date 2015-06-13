package env2.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import math.MyMath;
import math.MyPoint2D;
import env2.frustrum.AbstractFrustrum;
import env2.frustrum.FrustrumCircleN;
import env2.frustrum.FrustrumCrossN;
import env2.instanciator.actions.AbstractActionInstanciator;
import env2.instanciator.factory.ActionFactory;
import env2.resources.CommonResource;
import env2.type.Direction;
import env2.type.EffectType;
import env2.type.FrustrumType;
import env2.type.InfluenceType;
import env2.type.Time;
import env2.type.WorldObjectType;
import gui.GUI;

public abstract class AbstractBody extends AbstractMobileWorldObject implements InterfaceObservable{
	/* Observable interface variable */
	private List<InterfaceObserver> observers = new ArrayList<InterfaceObserver>();
	
	/*
	 * Offsets introduction with these variables representing
	 * absolute errors on the corresponding variables.
	 * 
	 * WARNING
	 * Definition in % so that we keep same errors on all
	 * types of bodies.
	 * 
	 * They're here for the moment, but as another enhancement
	 * we might redefine them.
	 */
	private static final int DELTA_TRANSPORT_CAPA = 10; // %
	private static final int DELTA_EATING_CAPA = 10;	// %
	private static final int DELTA_STRENGTH = 15;		// %
	private static final int DELTA_DEFENSE = 15;		// %
	private static final int DELTA_MAX_AGE = 20;		// %
	private static final int DELTA_ADULT_AGE = 25;		// %
	private static final int DELTA_MAX_LIFE = 10;		// %
	private static final int DELTA_LIFE_LOSS = 5;		// %
	
	/*
	 * Final attrbiutes for all bodies: characteristics.
	 * Definition inside following in-line comments.
	 * 
	 * We assume everything is defined with integers.
	 * Actually, it doesn't change anything:
	 * 		the complexity is less important,
	 * 		and it's nothing but a hidden scaling.
	 */
	private final int TRANSPORT_CAPA;			// Max weight (quantity) that could be carried
	private final int EATING_CAPA;				// Max weight (quantity) that could be eaten at once
	private final int MOVING_REACH;				// Max number of cases to be crossed at once
	private final int VISION_REACH;				// Max vision distance (nb of cases) linked with Frustrum def
	private final int STRENGTH;					// Strength, in a given unit
	private final int DEFENSE;					// Defense, in a given unit: resistance to applied damage
	private final int MAX_AGE;					// Max age of the given entity, in seconds
	private final int ADULT_AGE;				// Time starting from which the body is no longer a baby, in seconds
	private final int MAX_LIFE;					// Max life for a given entity, in vitality points
	private final int LIFE_LOSS;				// Regular life loss, at each turn
	private final int CREATION_TIME;			// Time of creation, in seconds
	private final int TRIBE_ID;					// Tribe ID to help our guys to recognize pheromones and friends
	private final int MAX_BODY_SIZE;			// Maximum body size, in % (between 0 and 100)
	private final FrustrumType FRUSTRUM_TYPE; 	// Type of frustrum for this entity.
	
	/*
	 * For-use attributes.
	 */
	private ArrayList<AbstractResource> mycargo;
	private AbstractFrustrum myfrustrum;
	private AbstractEnvironment myenv;
	private Direction mydir;
	private MyPoint2D mypos;
	private int mylife;
	private int bonusstrength;
	private ArrayList<AbstractInfluence> myinfluences;
	
	/**
	 * Constructor to be used by all inheriting classes.
	 * For internal use only.
	 */
	protected AbstractBody(int transport_capa,
							int eating_capa,
							int moving_reach,
							int vision_reach,
							int strength,
							int defense,
							int adult_age,
							int max_age,
							int max_life,
							int life_loss,
							int creation_time,
							int tribe_id,
							int max_body_size,
							FrustrumType frustrum_type,
							AbstractEnvironment env,
							Direction dir,
							MyPoint2D pos) {
		
		TRANSPORT_CAPA = transport_capa + MyMath.random((int)(-DELTA_TRANSPORT_CAPA/100.0f * transport_capa), (int)(DELTA_TRANSPORT_CAPA/100.0f * transport_capa));
		EATING_CAPA = eating_capa + MyMath.random((int)(-DELTA_EATING_CAPA/100.0f * eating_capa), (int)(DELTA_EATING_CAPA/100.0f * eating_capa));
		MOVING_REACH = moving_reach;
		VISION_REACH = vision_reach;
		STRENGTH = strength + MyMath.random((int)(-DELTA_STRENGTH/100.0f * strength), (int)(DELTA_STRENGTH/100.0f * strength));
		DEFENSE = defense + MyMath.random((int)(-DELTA_DEFENSE/100.0f * defense), (int)(DELTA_DEFENSE/100.0f * defense));
		ADULT_AGE = adult_age + MyMath.random((int)(-DELTA_ADULT_AGE/100.0f * adult_age), (int)(DELTA_ADULT_AGE/100.0f * adult_age));
		MAX_AGE = max_age + MyMath.random((int)(-DELTA_MAX_AGE/100.0f * max_age), (int)(DELTA_MAX_AGE/100.0f * max_age));
		MAX_LIFE = max_life + MyMath.random((int)(-DELTA_MAX_LIFE/100.0f * max_life), (int)(DELTA_MAX_LIFE/100.0f * max_life));
		LIFE_LOSS = life_loss + MyMath.random((int)(-DELTA_LIFE_LOSS/100.0f * life_loss), (int)(DELTA_LIFE_LOSS/100.0f * life_loss));
		CREATION_TIME = creation_time;
		TRIBE_ID = tribe_id;
		MAX_BODY_SIZE = max_body_size;
		FRUSTRUM_TYPE = frustrum_type;
		
		myenv = env;
		mydir = dir;
		mypos = pos;
		
		mylife = MAX_LIFE;
		bonusstrength = 0;
		mycargo = new ArrayList<>();
		myinfluences = new ArrayList<>();
		myfrustrum = null;
		
		move(env, pos);
		
		GUI gui = GUI.getInstance();
		this.addObserver(gui);
		this.fireEvent(10);
	}
	
	/*************************************************************************
	 * For-use methods: Tools.
	 *************************************************************************/
	
	public void buildNewFrustrum() {
		switch(FRUSTRUM_TYPE) {
		case CROSS:
			myfrustrum = new FrustrumCrossN(this, myenv, getVisionReach());
			break;
			
		default:
			myfrustrum = new FrustrumCircleN(this, myenv, getVisionReach());
		}
	}
	
	public boolean isFriend(AbstractBody b) {
		return b.TRIBE_ID == TRIBE_ID;
	}
	
	public void applyLifeVariation(int var) {
		mylife += var;
		if (mylife > MAX_LIFE)
			mylife = var;
	}
	
	public void applyStrengthVariation(int var) {
		bonusstrength += var;
		if (getStrength() < 0)
			bonusstrength = -STRENGTH;
	}
	
	/*
	 * This method should be overriden with all possible resource.
	 * The effect is hard-coded inside for each kind of body.
	 * 
	 * INSIDE THIS METHOD, WE ONLY CHECK AND APPLY THE EFFECTS
	 * AFTER EATING THIS MUCH OF THIS RESOURCE
	 */
	public void applyEffectsForEating(AbstractResource o, int qty) {
		switch(o.getType()) {
		case ROCK:
			applyEffectsForEatingRock(o, qty);
			break;
			
		case WOOD:
			applyEffectsForEatingWood(o, qty);
			break;
			
		case LEAF:
			applyEffectsForEatingLeaf(o, qty);
			break;
			
		case MEAT:
			applyEffectsForEatingMeat(o, qty);
			break;
			
		case SUGAR:
			applyEffectsForEatingSugar(o, qty);
			break;
			
		case FRUIT:
			applyEffectsForEatingFruit(o, qty);
			break;
			
		case POISON:
			applyEffectsForEatingPoison(o, qty);
			break;
			
		case GAS:
			applyEffectsForEatingGas(o, qty);
			break;
			
		default:
			return;
		}
	}
	
	public abstract void applyEffectsForEatingRock(AbstractResource o, int qty);
	public abstract void applyEffectsForEatingWood(AbstractResource o, int qty);
	public abstract void applyEffectsForEatingLeaf(AbstractResource o, int qty);
	public abstract void applyEffectsForEatingMeat(AbstractResource o, int qty);
	public abstract void applyEffectsForEatingSugar(AbstractResource o, int qty);
	public abstract void applyEffectsForEatingFruit(AbstractResource o, int qty);
	public abstract void applyEffectsForEatingPoison(AbstractResource o, int qty);
	public abstract void applyEffectsForEatingGas(AbstractResource o, int qty);
	
	/*
	 * These methods create a corresponding pheromone and place it on the cell
	 * then, the pheromone spreads...
	 * 
	 * TODO: add some parameters to these pheromones methods (qty, ...)
	 */
	public abstract void producePheromoneDanger();
	public abstract void producePheromoneFood();
	
	public boolean isBaby(float TIME) {
		return getAge(TIME) < ADULT_AGE;
	}
	
	/**
	 * Check if a body carries its maximal weight.
	 * /!\ Warning ! The quantities of resources carried are negatives.
	 * @return boolean
	 */
	public boolean isFull(){
		List<AbstractResource> resourcesCarried = this.getCargo();
		
		int capacity = this.getTransportCapacity();
		
		for(AbstractWorldObject object : this.getCargo()){
			capacity = capacity + ( ((CommonResource)object).getQuantity()-100);
		}
		
		if(capacity>0){
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	/* this method only takes care of moving the body to the arrival case
	 * nothing else is done here
	 * (influences solving/distance checking etc)
	 * hence, you should not forget to erase the current frustrum
	 * 
	 * if (newenv != null &&
	 * 		newpos.getX() >= 0 && newpos.getX() < newenv.getWidth() &&
	 * 		newpos.getY() >= 0 && newpos.getY() < newenv.getHeight())
	 * 
	 * (non-Javadoc)
	 * @see env2.api.AbstractMobileWorldObject#move(env2.api.AbstractCell)
	 */
	public boolean move(AbstractEnvironment newenv, MyPoint2D newpos) {
		newenv.getCell(newpos.getX(), newpos.getY()).addObject(this);
		myenv.getCell(mypos.getX(), mypos.getY()).removeObject(this);
		myenv = newenv;
		mypos = newpos;
		return true;
	}
	
	/*************************************************************************
	 * Getters and setters.
	 *************************************************************************/
	
	public AbstractFrustrum getCurrentFrustrum() {
		if (myfrustrum == null)
			buildNewFrustrum();
		return myfrustrum;
	}
	
	public AbstractEnvironment getEnvironment() {
		return myenv;
	}
	
	public MyPoint2D getPosition() {
		return mypos;
	}

	public Direction getDirection() {
		return mydir;
	}
	
	public int getLife() {
		return mylife;
	}
	
	/*
	 * Must be redefined in each child...
	 * Because each child should have its own list of Effects...
	 */
	protected abstract HashMap<WorldObjectType, EffectType> getEffects();
	
	public EffectType getEffect(AbstractWorldObject o) {
		if (getEffects().containsKey(o.getType()))
			return getEffects().get(o.getType());
		return EffectType.NEUTRAL;
	}
	
	public int getTransportCapacity() {
		return TRANSPORT_CAPA;
	}
	
	public int getEatingCapacity() {
		return EATING_CAPA;
	}
	
	public int getMovingReach() {
		return MOVING_REACH;
	}
	
	public int getVisionReach() {
		return VISION_REACH;
	}
	
	public int getStrength() {
		return STRENGTH + bonusstrength;
	}
	
	public int getDefense() {
		return DEFENSE;
	}
	
	public int getMaxAge() {
		return MAX_AGE;
	}
	
	public int getAge(float TIME) {
		return (int) (TIME - CREATION_TIME);
	}
	
	public boolean isPerceivable() {
		return true;
	}
	
	/*
	 * Size of this body at TIME seconds
	 * size between 10% to 100% of MAX_BODY_SIZE
	 */
	public int getSize(float TIME) {
		if (!isBaby(TIME))
			return 100;
		
		int t = getAge(TIME)/ADULT_AGE;
		return (int) (t * (0.1 * MAX_BODY_SIZE) + (1-t) * (MAX_BODY_SIZE));
	}
	
	public int getLifeLoss() {
		return LIFE_LOSS;
	}
	
	public List<AbstractResource> getCargo() {
		return mycargo;
	}
	


	protected int _getTribeID() {
		return TRIBE_ID;
	}
	
	/***/
		
	public List<AbstractAction> solveInfluences() {
		List<AbstractAction> actions = new LinkedList<>();
		
		// TODO
		
		for (AbstractInfluence influence : myinfluences) {
			switch (influence.getType()) {
			case ATTACK_CURE:
				AbstractActionInstanciator.influence = influence;
				actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.ATTACK_CURE).getAction());
				break;
				
			case EAT:
				AbstractActionInstanciator.influence = influence;
				actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.EAT).getAction());
				break;
				
			case KILL_MYSELF:
				myinfluences.clear();
				actions.clear();
				AbstractActionInstanciator.influence = influence;
				actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.KILL_MYSELF).getAction());
				return actions;
			
			case BURY_DEAD:
					myinfluences.clear();
					actions.clear();
					AbstractActionInstanciator.influence = influence;
					actions.add(ActionFactory.ACTION_INSTANCIATOR.get(InfluenceType.BURY_DEAD).getAction());
					return actions;
			
			default:
				System.out.println("Influence on body not treated: " + influence);
			}
		}
		
		myinfluences.clear();
		return actions;
	}
	
	public void addInfluenceHere(AbstractInfluence influence) {
		getEnvironment().getCell(getPosition()).addInfluence(influence);
	}
	
	public void addInfluence(AbstractInfluence influence) {
		myinfluences.add(influence);
	}
	
	public boolean isDead() {
		// TODO Check TIME
		return getAge(Time.getTime()) > MAX_AGE || getLife() < 0;
	}

	
	public int getMaxLife(){
		return MAX_LIFE;
	}
	
	public AbstractCell getCell() {
		return getEnvironment().getCell(mypos);
	}
		
	/*************************************************************************
	 * Main method: tests...
	 * 
	 * Test for interpolation formula: seems OK.
	 *************************************************************************/
	
	public static void main(String[] args) {
		int AGE = 0;
		int ADULT_AGE = 15;
		int MAX_BODY_SIZE = 80;
		
		int t = AGE/ADULT_AGE;
		int interpsize = (int) ((1-t) * (0.1 * MAX_BODY_SIZE) + (t) * (MAX_BODY_SIZE));
		System.out.println("INTERP SIZE: " + interpsize);
	}
	
	/***/

	/* Observable interface functions */
	public void addObserver(InterfaceObserver i)
	{
		if(i != null)		this.observers.add(i);
	}
    public void removeObserver(InterfaceObserver i)
	{
		if(i != null)		this.observers.remove(i);		
	}
    public void fireEvent(int eventCode)
	{
		for(InterfaceObserver i : this.observers)
			i.eventFired(this, eventCode);
	}
}
