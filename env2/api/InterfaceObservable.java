package env2.api;

public interface InterfaceObservable {
	public void addObserver(InterfaceObserver i);
    public void removeObserver(InterfaceObserver i);
    public void fireEvent(int eventCode);
}
