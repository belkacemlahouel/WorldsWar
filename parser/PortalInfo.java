package parser;

class PortalInfo{
	int env1;
	int env2;
	int posEnv1[] = new int[2];
	int posEnv2[] = new int[2];

	/**
	 * Print the informations of PortalInfo.
	 */
	public void printInfos()
	{
		System.out.println("Portal from env "+ env1 + " in position " + posEnv1[0] + "," + posEnv1[1] + " to env " +env2+" in position " + posEnv2[0] + "," + posEnv2[1]);
	}
}