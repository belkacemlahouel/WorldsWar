package env2.api;

public interface InterfaceGatherer {
	
	public abstract void take(AbstractWorldObject o, int qty);
	public abstract int getStdTakeQty();
	public abstract int getRndTakeQty();
	public abstract int getTotTakeQty();
}