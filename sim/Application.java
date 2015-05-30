package sim;

import java.io.IOException;

public class Application {

	public static void main(String[] args) throws IOException {
		
		Simulator sim = Simulator.getInstance("src/res/conf/ONLY_ONE_MOTHER");
		
		while (true) {
			sim.doStep();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
