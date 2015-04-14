package env2.api;

public interface InterfaceSoldier {

	public abstract void hit(AbstractBody o);
	
	public abstract int getStdDmg();
	public abstract int getRndDmg();
	public abstract int getTotDmg();
}
