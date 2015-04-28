package parser;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * The structure of the file parsed is the following
 * globalSize=int*int	//the size of the global environment
 * global=char[]		//char[] is an array of char, where e means earth (for a cell of earth), and w means water
 * antSize=int*int		//size of the cave of the ants
 * ant=char[]			//char[] is an array of char, where w means wall, and t means tunnel
 * termiteSize=2*2		//size of the cave of the termites
 * termite=char[]		//char[] is an array of char, where w means wall, and t means tunnel
 * portals=[g,0,1
 * ressources=
 */
class EnvironmentParser {
	
	private Path fFilePath;
	private char globalEnv[][];
	private char AntsCave[][];
	private char TermitesCave[][];
	private int portals[];
	private char ressources[];
	
	
	public EnvironmentParser()
	{
	    fFilePath = null;
	    globalEnv = null;
	    AntsCave = null;
	    TermitesCave =null;
	    portals = null;
	    ressources = null;
	}
	
	/**
	* Constructor.
	* @param fileName full name of an existing, readable file.
	*/
	public EnvironmentParser(String fileName)
	{
	    fFilePath = Paths.get(fileName);
	    globalEnv = null;
	    AntsCave = null;
	    TermitesCave =null;
	    portals = null;
	    ressources = null;
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
		//use a second Scanner to parse the content of each line 
	    Scanner scanner = new Scanner(line);
	    scanner.useDelimiter("=");
	    if (scanner.hasNext()){
	      //assumes the line has a certain structure
	      String type = scanner.next();
	      if(type.equals("name"))
	      {
	    	  String value = scanner.next();
	    	  System.out.println("My name is "+ value );
	      }
	      else
	      {
	    	  String value = scanner.next();
	    	  System.out.println("I am in the "+ value );
	      }
	      
	    }
	    else {
	    	System.out.println("Invalid or empty line !");
	    }
	    
	    scanner.close();
	}
}