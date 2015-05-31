package sim;

public class Application {

	public static void main(String[] args) {
		
		String fileName = "src/res/conf/ONLY_ONE_MOTHER";
		Simulator sim = Simulator.getInstance(fileName);
		
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
