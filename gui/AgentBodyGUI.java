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
import env.Body;
import gui.GUI;



public class AgentBodyGUI {
	public JPanel container;
	public Body agentBody;
	public String species;
	public ImageIcon bodyRepr;
	public int posX;
	public int posY;
	
	/*
	 * Constructor of the class AgentBodyGUI
	 */
	public AgentBodyGUI(String species, GUI gui, Body body)
	{
		agentBody = body;
		container = new JPanel();
		
		//initial positions for the differents species.
		int POSX_INIT_ANT = 0;
		int POSY_INIT_ANT = 0;
		int POSX_INIT_TERMITE = 100;
		int POSY_INIT_TERMITE = 100;
		int POSX_INIT_SPIDER = 50;
		int POSY_INIT_SPIDER = 50;
		
		if(species.equalsIgnoreCase("Ant"))
		{
			bodyRepr = new ImageIcon("src/ressource/gui/ant.png");
			posX = POSX_INIT_ANT;
			posY = POSY_INIT_ANT;
		}
		else if(species.equalsIgnoreCase("Ant"))
		{
			bodyRepr = new ImageIcon("src/ressource/gui/termite.png");
			posX = POSX_INIT_TERMITE;
			posY = POSY_INIT_TERMITE;
		}
		else
		{
			//TODO create the spider.png file.
			bodyRepr = new ImageIcon("src/ressource/gui/spider.png");
			posX = POSX_INIT_SPIDER;
			posY = POSY_INIT_SPIDER;
		}
		
		// print the panel in the GUI and add the representation in the panel
		container.add(new JLabel(bodyRepr));
		container.setSize(10, 10);
		gui.add(container);
		
		container.setLocation(posX,posY);
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
		
		//move the panel of the body's representation
		container.setLocation(x,y);
	}
}