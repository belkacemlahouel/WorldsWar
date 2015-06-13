package sim;

public class Application {

	public static void main(String[] args) {

		final int TIME_STEP = 1000; // in ms 
//		final String fileName = "src/res/conf/ONLY_ONE_MOTHER";
//		final String fileName = "src/res/conf/TEST2";
		final String fileName = "src/res/conf/ONLY_ONE_GATHERER";
//		final String fileName = "src/res/conf/ONLY_ONE_GATHERER_FOOD";
		
		/***/
		
		Simulator sim = Simulator.getInstance(fileName);
		
		while (!sim.isFinished()) {
			sim.doStep();
			
			try {
				Thread.sleep(TIME_STEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Bye World!");
	}
	
}
