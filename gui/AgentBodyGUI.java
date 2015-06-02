/*
 * Graphic User Interface for the body of an agent.
 * The representation of that body will be different in function of the "species" of the agent.
 * The differents species are ANTS, TERMITES and SPIDERS.
 * Last Update : 12/04/15
 * Version : 1.0
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import math.MyPoint2D;
import env2.api.AbstractBody;
import gui.GUI;
import java.awt.Rectangle;



public class AgentBodyGUI {
	public JPanel container;
	public AbstractBody agentBody;
	public ImageIcon bodyRepr;
	private GUI guiContainer;

	
	/*
	 * Constructor of the class AgentBodyGUI
	 */
	public AgentBodyGUI(String species, GUI gui, AbstractBody body)
	{
		agentBody = body;
		container = new JPanel();
		guiContainer = gui;
		MyPoint2D coordinates = agentBody.getPosition();
		
		int posX = coordinates.getX();
		int posY = coordinates.getY();
		
		int width;
		int height;
		
		if(species.equalsIgnoreCase("Ant"))
		{
			bodyRepr = new ImageIcon("src/res/gui/ant.png");
		}
		else if(species.equalsIgnoreCase("Ant"))
		{
			bodyRepr = new ImageIcon("src/res/gui/termite.png");
		}
		else
		{
			//TODO create the spider.png file.
			bodyRepr = new ImageIcon("src/res/gui/spider.png");
		}
		
		/* Use the camera to put the agent body gui at the right place */
		Rectangle rectPos;
		Camera camera = guiContainer.getCamera();
		
		rectPos = camera.logical2absolutePixel(agentBody.getEnvironment(), posX, posY);
		System.out.println(rectPos);
		
		if(rectPos != null){
			posX = (int) rectPos.getX();
			posY = (int) rectPos.getY();

			width = (int) rectPos.getWidth();
			height = (int) rectPos.getHeight();
		}else{
			posX = 0;
			posY = 0;

			width = 10;
			height = 10;
		}
		
		
		// print the panel in the GUI and add the representation in the panel
		container.add(new JLabel(bodyRepr));

		container.setSize(width, height);		
		container.setLocation(posX, posY);
		
		gui.getAntPanel().add(container);
		
		container.setLocation(posX,posY);
		container.setOpaque(false);
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
		Camera camera = guiContainer.getCamera();
		
		rectPos = camera.logical2absolutePixel(agentBody.getEnvironment(), x, y);
		
		if(rectPos != null){
			x = (int) rectPos.getX();
			y = (int) rectPos.getY();
		}else{
			x = 0;
			y = 0;
		}
		
		System.out.println(x+" and "+y);
		
		//move the panel of the body's representation
		container.setLocation(x,y);
	}
}
