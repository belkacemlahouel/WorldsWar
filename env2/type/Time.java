package env2.type;

public class Time {
	
	public static int instanciation_time = getTime();
		
	public static void initTime() {
		instanciation_time = getTime();
		last_update_time = instanciation_time;
	}
	
	public static int getTime() {
		return (int) (1000*System.currentTimeMillis()) - instanciation_time;
	}
	
	/***/
	
	public static int last_update_time = instanciation_time;
	
	public static int getDeltaTime() {
		int delta = getTime() - last_update_time;
		last_update_time = getTime();
		return delta;
	}
}
