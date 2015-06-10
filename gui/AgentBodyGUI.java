/*
 * Graphic User Interface for the body of an agent.
 * The representation of that body will be different in function of the "species" of the agent.
 * The differents species are ANTS, TERMITES and SPIDERS.
 * Last Update : 12/04/15
 * Version : 1.0
 */
package gui;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import math.MyPoint2D;
import env2.api.AbstractBody;


public class AgentBodyGUI {
	public JPanelImage container;
	public AbstractBody agentBody;
	public BufferedImage bodyRepr;
	private GUI gui;
	
	private static final Random rd = new Random();
	private final float startX;
	private final float startY;
	
	/*
	 * Constructor of the class AgentBodyGUI
	 */
	public AgentBodyGUI(String species, GUI gui, AbstractBody body)
	{
		this.gui = gui;
		
		agentBody = body;
		MyPoint2D coordinates = agentBody.getPosition();
		
		int posX = coordinates.getX();
		int posY = coordinates.getY();
		
		startX = rd.nextFloat()%0.75f;
		startY = rd.nextFloat()%0.75f;
		
		if(species.equalsIgnoreCase("Ant"))
		{
			bodyRepr = loadImage("src/res/gui/ant.png");
		}
		else if(species.equalsIgnoreCase("Termine"))
		{
			bodyRepr = loadImage("src/res/gui/termite.png");
		}
		else if(species.equalsIgnoreCase("Spider"))
		{
			bodyRepr = loadImage("src/res/gui/spider.png");
		}
		else
			bodyRepr = loadImage("src/res/gui/ant.png");

		container = new JPanelImage(bodyRepr);
		
		/* Use the camera to put the agent body gui at the right place */
		Rectangle rectPos;
		Camera camera = this.gui.getCamera();
		rectPos = camera.logical2absolutePixel(agentBody.getEnvironment(), posX, posY);		
		
		int width, height;
		
		if(rectPos != null){			
			width = (int) rectPos.getWidth();
			height = (int) rectPos.getHeight();
			
			posX = (int) (rectPos.getX() + width*startX);
			posY = (int) (rectPos.getY() + height*startY);
		}else{
			width = 0;
			height = 0;
			
			posX = 0;
			posY = 0;
		}		
		
		// print the panel in the GUI and add the representation in the panel
		container.setSize(width, height);		
		container.setLocation(posX, posY);

		container.setLocation(posX,posY);
		container.setOpaque(false);
		
		this.gui.getWorldObjectPanel().add(container);		
	}
	
	/*
	 * Get the x-coordinate of the AgentBody
	 */
	public int getPosXBody()
	{
		MyPoint2D coordinates = agentBody.getPosition();
		int x = coordinates.getX();
		return x;
	}
	
	/*
	 * Get the y-coordinate of the AgentBody
	 */
	public int getPosYBody()
	{
		MyPoint2D coordinates = agentBody.getPosition();
		int y = coordinates.getY();
		return y;
	}
	
	/*
	 * Update the position of the AgentBody
	 */
	public void move()
	{
		int x = getPosXBody();
		int y = getPosYBody();
		Rectangle rectPos;
		Camera camera = this.gui.getCamera();
		
		rectPos = camera.logical2absolutePixel(agentBody.getEnvironment(), x, y);
		
		int width, height;
		
		if(rectPos != null){			
			width = (int) rectPos.getWidth();
			height = (int) rectPos.getHeight();
			
			x = (int) (rectPos.getX() + width*startX);
			y = (int) (rectPos.getY() + height*startY);
		}else{
			width = 0;
			height = 0;
			
			x = 0;
			y = 0;
		}
		
		if(agentBody.isDead()){
			bodyRepr = loadImage("src/res/gui/dead_ant.png");
			container.setImage(bodyRepr);
		}
		
		//move the panel of the body's representation
		container.setSize((int) rectPos.getWidth(), (int) rectPos.getHeight());		
		container.setLocation(x,y);
	}
	
	private BufferedImage loadImage(String filename)
	{
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(filename));
		} catch (IOException e) {
		}
		
		return img;
	}
}
