package env2.type;

/**
 * This class is used to quickly determine
 * whether a food is good, bad, etc...
 * Then, we have (for each specie) to overide
 * eat() with the particular food to be treated
 * inside the body (digestion simulation).
 * 
 * We may have food with several effects such as
 * life improvement and strength improvement...
 * 
 * We suppose that one kind of food does not have
 * a bad effect + a good effect, one effect
 * generally overpasses the other or the effect
 * is of the same kind in several areas.
 *  
 * @author belka
 *
 */

public enum EffectType {
	
	VERYBAD(-2),
	BAD(-1),
	NEUTRAL(0),
	GOOD(1),
	VERYGOOD(2);
	
	public final int value;
	
	private EffectType(int val) {
		value = val;
	}
	
	public int getValue() {
		return value;
	}
}
