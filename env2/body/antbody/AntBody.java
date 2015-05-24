package env2.body.antbody;

import java.util.HashMap;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.type.Direction;
import env2.type.EffectType;
import env2.type.FrustrumType;
import env2.type.WorldObjectType;

public abstract class AntBody extends AbstractBody {
	
	private final static HashMap<WorldObjectType, EffectType> MYEFFECTS;
	
	static {
		MYEFFECTS = new HashMap<WorldObjectType, EffectType>();
		MYEFFECTS.put(WorldObjectType.ROCK, EffectType.NEUTRAL);
		MYEFFECTS.put(WorldObjectType.WOOD, EffectType.NEUTRAL);
		MYEFFECTS.put(WorldObjectType.LEAF, EffectType.GOOD);
		MYEFFECTS.put(WorldObjectType.MEAT, EffectType.GOOD);
		MYEFFECTS.put(WorldObjectType.SUGAR, EffectType.VERYGOOD);
		MYEFFECTS.put(WorldObjectType.FRUIT, EffectType.GOOD);
		MYEFFECTS.put(WorldObjectType.POISON, EffectType.VERYBAD);
		MYEFFECTS.put(WorldObjectType.GAS, EffectType.BAD);
	}
	
	public AntBody(int TIME,
					int tribe_id,
					AbstractEnvironment env,
					Direction dir,
					MyPoint2D pos) {
		
		super(10,						// transport_capa
				1,						// eating_capa
				2,						// moving_reach
				5,						// vision_reach
				3,						// strength
				1,						// defense
				10,						// adult_age
				100,					// max_age
				70, 					// max_life
				2,						// life_loss
				TIME,					// creation_time
				tribe_id,				// tribe_id
				80,						// max_body_size
				FrustrumType.CIRCLE,	// frustrum_type
				env,					// env
				dir,					// dir
				pos);					// pos
	}

	/*
	 * Rules regarding the digestion of each kind of food
	 * 
	 * (non-Javadoc)
	 * @see env2.api.AbstractBody#applyEffectsForEating(env2.api.AbstractResource, int)
	 */
	
	public void applyEffectsForEatingRock(AbstractResource o, int qty) {
		// Nothing to do, the effect is neutral
	}
	
	public void applyEffectsForEatingWood(AbstractResource o, int qty) {
		// Nothing to do, the effect is neutral
	}
	
	public void applyEffectsForEatingLeaf(AbstractResource o, int qty) {
		applyLifeVariation(qty);
	}
	
	public void applyEffectsForEatingMeat(AbstractResource o, int qty) {
		applyStrengthVariation(qty);
	}
	
	public void applyEffectsForEatingSugar(AbstractResource o, int qty) {
		applyLifeVariation(qty * 2);
		applyStrengthVariation(qty * 4);
	}
	
	public void applyEffectsForEatingFruit(AbstractResource o, int qty) {
		applyLifeVariation(qty * 4);
		applyStrengthVariation(qty * 2);
	}
	
	public void applyEffectsForEatingPoison(AbstractResource o, int qty) {
		applyLifeVariation(qty * -2);
		applyStrengthVariation(qty * -2);
	}
	
	public void applyEffectsForEatingGas(AbstractResource o, int qty) {
		applyLifeVariation(qty * -1);
	}
	
	/*
	 * Pheromones management.
	 * We put here the rules regarding pheromones creation, for each specie.
	 * TODO Pheromones parameters when instanciating
	 * 
	 * (non-Javadoc)
	 * @see env2.api.AbstractBody#producePheromoneDanger()
	 */

	@Override
	public void producePheromoneDanger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void producePheromoneFood() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Classic getType method.
	 * And getEffects since we cannot store them easily in AbstractBody.
	 * 
	 * (non-Javadoc)
	 * @see env2.api.AbstractWorldObject#getType()
	 */

	@Override
	public WorldObjectType getType() {
		return WorldObjectType.ANTBODY;
	}

	@Override
	protected HashMap<WorldObjectType, EffectType> getEffects() {
		return MYEFFECTS;
	}
}
