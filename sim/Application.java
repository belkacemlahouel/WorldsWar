package sim;

public class Application {

	public static void main(String[] args) {
		
		Simulator sim = Simulator.getInstance("src/res/conf/ONLY_ONE_MOTHER");
		
		while (!sim.isFinished()) {
			sim.doStep();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Bye World!");
	}
	
}
