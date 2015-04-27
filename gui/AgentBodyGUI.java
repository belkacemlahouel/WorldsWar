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



public class AgentBodyGUI {
	public JPanel container;
	public AbstractBody agentBody;
	public ImageIcon bodyRepr;

	
	/*
	 * Constructor of the class AgentBodyGUI
	 */
	public AgentBodyGUI(String species, GUI gui, AbstractBody body)
	{
		agentBody = body;
		container = new JPanel();
		MyPoint2D coordinates = agentBody.getPosition();
		
		int posX = coordinates.getX();
		int posY = coordinates.getY();
		
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
		
		// print the panel in the GUI and add the representation in the panel
		container.add(new JLabel(bodyRepr));
		container.setSize(10, 10);
		container.setLocation(posX,posY);
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
