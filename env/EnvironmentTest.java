package env;

import env.environment.Environment;
import env.environment.EnvironmentGround;

public class EnvironmentTest {

	public static void main(String[] args) {
		
		System.out.println("Hey world!");
		
		Environment env = new EnvironmentGround(10, 7);
		
		System.out.println("Bye world!");
	}
}
