package sim;

import java.io.IOException;

public class Application {

	public static void main(String[] args) throws IOException {
		
		Simulator sim = new Simulator();
		
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
