package parser;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;


class EnvironmentParser {
	
	private Path fFilePath;
	private List<Integer> envsHeight;
	private List<Integer> envsWidth;
	private List<String> tribes;
	private List<PortalInfo> portals;
	private List<ResourceInfo> resources;
	private List<BodyInfo> bodies;
	
	
	public EnvironmentParser()
	{
	    fFilePath = null;
	}
	
	/**
	* Constructor.
	* @param fileName full name of an existing, readable file.
	*/
	public EnvironmentParser(String fileName)
	{
	    fFilePath = Paths.get(fileName);
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
		
		//use a second Scanner to parse the content of each line 
	    Scanner scanner = new Scanner(line);
	    scanner.useDelimiter("=");
	    if (scanner.hasNext())
	    {
	      //assumes the line has a certain structure
	      String type = scanner.next();	      
	      value = scanner.next();
	      
	      System.out.println(type +" is ");
	      
	    }
	    else
	    {
	    	value = "";
	    }
	    
	    //--------------------
	    Scanner valueScanner = new Scanner(value);
	    valueScanner.useDelimiter(" ");
	    while(valueScanner.hasNext())
		{
	    	System.out.println(" ("+ valueScanner.next() + ") ");
		}
	    valueScanner.close();
	    //-------------
	      
	    scanner.close();
	}
}