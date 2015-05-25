package parser;

public class PortalInfo extends AbstractInfo {
	
	public int env1;
	public int env2;
	public int[] posEnv1;
	public int[] posEnv2;
	
	public PortalInfo() {
		posEnv1 = new int[2];
		posEnv2 = new int[2];
	}

	/**
	 * Print the information of PortalInfo.
	 */
	public void printInfos() {
		System.out.println("Portal from env " + env1 + " in position " + posEnv1[0] + ", " + posEnv1[1] + " to env " + env2 + " in position " + posEnv2[0] + ", " + posEnv2[1]);
	}
}