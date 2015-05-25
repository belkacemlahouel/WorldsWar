package parser;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

import env2.type.WorldObjectType;
import parser.ConfParameters;


public class EnvironmentParser {
	
	private Path fFilePath;
	
	private LinkedList<Integer> envsHeights; // heights and widths for each environment, in order
	private LinkedList<Integer> envsWidths;
	
	private LinkedList<PortalInfo> portals;
	
	private HashMap<Integer,String> tribes = new HashMap<Integer,String>();
	private List<ResourceInfo> resources = new ArrayList<ResourceInfo>();
	private List<BodyInfo> bodies = new ArrayList<BodyInfo>();
	
	/***/
	
	// TODO @rdorier
	private HashMap<WorldObjectType, LinkedList<ResourceInfo>> resources2;
	private HashMap<WorldObjectType, LinkedList<BodyInfo>> bodies2;
	private LinkedList<WorldObjectType> tribes2;
	
	/***/
	
	/**
	* Constructor.
	* @param fileName full name of an existing, readable file.
	*/
	public EnvironmentParser(String fileName) {
	    fFilePath = Paths.get(fileName);
	    
	    envsHeights = new LinkedList<>();
	    envsWidths = new LinkedList<>();
	    portals = new LinkedList<>();
	    
	    resources2 = new HashMap<>();
	    bodies2 = new HashMap<>();
	    tribes2 = new LinkedList<>();
	}
	
	public EnvironmentParser() {
	    new EnvironmentParser(null);
	}
	
	/**
	* Parse the datas of the file located in the path saved in fFilePath.
	*/
	public void parseEnvDatas() throws IOException
	{
		try (Scanner scanner =  new Scanner(fFilePath)){
		      while (scanner.hasNextLine()){
		        parseLine(scanner.nextLine());
		      }      
		}
	}
	
	
	/**
	 * Treat a specific line.
	 * @param line, the line to parse.
	 */
	public void parseLine(String line)
	{
		String value;
		String type;
		String param;
		ConfParameters parameter = null;;
		int j,k;
		
		//use a second Scanner to parse the content of each line 
	    Scanner scanner = new Scanner(line);
	    scanner.useDelimiter("=");
	    if (scanner.hasNext())
	    {
	      //assumes the line has a certain structure
	      type = scanner.next();
	      parameter = ConfParameters.getByValue(type);
	      value = scanner.next();	      
	    }
	    else
	    {
	    	value = "";
	    	type = "";
	    }
	    
	    //use a third scanner to parse the parameters in value.
	    Scanner valueScanner = new Scanner(value);
	    valueScanner.useDelimiter(" ");
	    j=0;
	    while(valueScanner.hasNext())
		{
	    	param = valueScanner.next();

	    	switch (parameter) {
	    		case ENV :
	    			if(j%2 == 0)
	    			{
	    				envsWidths.add(Integer.parseInt(param));
	    			}
	    			else
	    			{
	    				envsHeights.add(Integer.parseInt(param));
	    			}
	    			break;
	    		
	    		case TRIBE :
	    			String tribeType = param;
	    			int id = Integer.parseInt(valueScanner.next());
	    			tribes.put(id, tribeType);
	    			break;
	    			
	    		case PORTAL:
	    			PortalInfo portal = new PortalInfo();
	    			portal.env1 = Integer.parseInt(param);
	    			portal.posEnv1[0] = Integer.parseInt(valueScanner.next());
	    			portal.posEnv1[1] = Integer.parseInt(valueScanner.next());
	    			valueScanner.next();
	    			portal.env2 = Integer.parseInt(valueScanner.next());
	    			portal.posEnv2[0] = Integer.parseInt(valueScanner.next());
	    			portal.posEnv2[1] = Integer.parseInt(valueScanner.next());
	    			portals.add(portal);
	    			break;
	    			
	    		case BODY:
	    			BodyInfo body = new BodyInfo();
	    			body.type = ConfParameters.getByValue(param);
	    			k=0;
	    			while(valueScanner.hasNext())
	    			{
	    				++k;
	    				if(k==1)
	    				{
	    					body.function = ConfParameters.getByValue(valueScanner.next());
	    				}
	    				else if(k==2)
	    				{
	    					body.tribeId = Integer.parseInt(valueScanner.next());
	    				}
	    				else if(k==3)
	    				{
	    					body.env = Integer.parseInt(valueScanner.next());
	    				}
	    				else if(k==4)
	    				{
	    					body.pos[0] = Integer.parseInt(valueScanner.next());
	    				}
	    				else if(k==5)
	    				{
	    					body.pos[1] = Integer.parseInt(valueScanner.next());
	    				}
	    				else
	    				{
	    					body.quantity = Integer.parseInt(valueScanner.next());
	    				}
	    			}
	    			bodies.add(body);
	    			break;
	    			
	    		case RES:
	    			ResourceInfo resource = new ResourceInfo();
	    			resource.type = ConfParameters.getByValue(param);
	    			k=0;
	    			while(valueScanner.hasNext())
	    			{
	    				++k;
	    				if(k==1)
	    				{
	    					resource.env = Integer.parseInt(valueScanner.next());
	    				}
	    				else if(k==2)
	    				{
	    					resource.posX = Integer.parseInt(valueScanner.next());
	    				}
	    				else if(k==3)
	    				{
	    					resource.posY = Integer.parseInt(valueScanner.next());
	    				}
	    				else
	    				{
	    					resource.quantity = Integer.parseInt(valueScanner.next());
	    				}
	    			}
	    			resources.add(resource);
	    			break;
	    			
	    		default :
	    			break;
	    	}
	    	++j;		
		}
	    valueScanner.close();
	    //-------------
	      
	    scanner.close();
	}
	
	
	/**
	 * Print the result of the parsing process.
	 */
	void print()
	{
		//print the environments
		for(int i = 0; i < envsHeights.size(); i++)
		{
            System.out.println("Environment "+ (i+1) + " is " + envsHeights.get(i) + "*" + envsWidths.get(i));
        }
		
		//print the different tribes.
		for (int i : tribes.keySet()) {
			System.out.println(tribes.get(i) + " " + i);
		}
		
		//print the portals
		for(int i = 0; i < portals.size(); i++)
		{
			portals.get(i).printInfos();
		}
		
		//print the bodies
		for(int i = 0; i < bodies.size(); i++)
		{
			bodies.get(i).printInfos();
		}
		
		//print the resources
		for(int i = 0; i < resources.size(); i++)
		{
			resources.get(i).printInfos();
		}
	}
	
	
	/**
	 * Getter on the list of integer containing the height of the environments.
	 * @return envsHeight
	 */
	public List<Integer> getEnvsHeights()
	{
		return envsHeights;
	}
	
	/**
	 * Getter on the list of integer containing the width of the environments.
	 * @return envsWidth
	 */
	public List<Integer> getEnvsWidths()
	{
		return envsWidths;
	}
	
	/**
	 * Getter on the list of bodies.
	 * @return bodies
	 */
	public List<BodyInfo> getBodies()
	{
		return bodies;
	}
	
	public int getNbGrounds() {
		if (envsWidths.size() != envsHeights.size())
			System.err.println("NB SIZES PARSED != NB HEIGTHS");
		return envsWidths.size();
	}
	
	public LinkedList<PortalInfo> getPortalInfos() {
		return portals;
	}
	
	public List<ResourceInfo> getResourcesInfos() {
		return resources;
	}
	
	/***/
	
	public HashMap<WorldObjectType, LinkedList<BodyInfo>> getBodies2() {
		return bodies2;
	}
	
	public HashMap<WorldObjectType, LinkedList<ResourceInfo>> getResources2() {
		return resources2;
	}
	
	public LinkedList<WorldObjectType> getTribes2() {
		return tribes2;
	}
}