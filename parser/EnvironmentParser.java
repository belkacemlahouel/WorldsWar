package parser;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;


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
		
	}
}