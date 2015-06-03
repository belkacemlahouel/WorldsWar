package sim;

import env2.type.Time;

public class Test {

	public static void main(String[] args) {
		while (true) {
			System.out.println(Time.getTime());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
