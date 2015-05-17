package env2.api;

public interface InterfaceNurse {

	public abstract void cure(AbstractBody o);
	
	public abstract int getStdCure();
	public abstract int getRndCure();
	public abstract int getTotCure();
}
