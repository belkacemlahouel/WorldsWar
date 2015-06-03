/*
 * Graphic User Interface for resources.
 * The representation of every reource will be different in function of its "type".
 * Version : 1.0
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.type.WorldObjectType;
import gui.GUI;

import java.awt.Rectangle;



public class ResourceGUI {
	public JPanel container;
	public AbstractResource resource;
	public ImageIcon resourceRepr;
	private GUI guiContainer;
	private MyPoint2D coordinates;
	private AbstractEnvironment env;

	
	/*
	 * Constructor of the class AgentBodyGUI
	 */
	public ResourceGUI(AbstractResource newResource, GUI gui, MyPoint2D position, AbstractEnvironment envContainer)
	{
		resource = newResource;
		container = new JPanel();
		guiContainer = gui;
		coordinates = position;
		env = envContainer;
		
		int posX = position.getX();
		int posY = position.getY();
		
		int width;
		int height;
		
		switch(resource.getType())
		{
			case ROCK :
				resourceRepr = new ImageIcon("src/res/gui/stone.png");
				break;
			case WOOD:
				resourceRepr = new ImageIcon("src/res/gui/wood.png");
				break;
			case LEAF :
				resourceRepr = new ImageIcon("src/res/gui/leaf.png");
				break;
			case MEAT:
				resourceRepr = new ImageIcon("src/res/gui/meat.png");
				break;
			case SUGAR :
				resourceRepr = new ImageIcon("src/res/gui/sugar.png");
				break;
			case FRUIT:
				resourceRepr = new ImageIcon("src/res/gui/fruit.png");
				break;
			case POISON :
				resourceRepr = new ImageIcon("src/res/gui/poison.png");
				break;
			case GAS:
				resourceRepr = new ImageIcon("src/res/gui/gas.png");
				break;
			default :
				break;
		}
		
		/* Use the camera to put the agent body gui at the right place */
		Rectangle rectPos;
		Camera camera = guiContainer.getCamera();
		rectPos = camera.logical2absolutePixel(env, posX, posY);
		
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
		container.add(new JLabel(resourceRepr));

		container.setSize(width, height);		
		container.setLocation(posX, posY);
		
		gui.getAntPanel().add(container);
		
		container.setLocation(posX,posY);
		container.setOpaque(false);
	}
	
	/*
	 * Get the x-coordinate of the resource.
	 */
	public int getPosXResource()
	{
		return coordinates.getX();
	}
	
	/*
	 * Get the y-coordinate of the resource.
	 */
	public int getPosYResource()
	{
		return coordinates.getY();
	}
	
	
	/**
	 * Modify the position of the resourceGUI.
	 * @param x, position on x-axis.
	 * @param y, position on y-axis.
	 */
	public void updatePosition(int x, int y)
	{
		MyPoint2D newPosition = new MyPoint2D(x, y);
		coordinates = newPosition;
		
		move();
	}
	
	
	/*
	 * Update the position of the AgentBody
	 */
	public void move()
	{
		int x = getPosXResource();
		int y = getPosYResource();
		Rectangle rectPos;
		Camera camera = guiContainer.getCamera();
		
		rectPos = camera.logical2absolutePixel(env, x, y);
		
		if(rectPos != null){
			x = (int) rectPos.getX();
			y = (int) rectPos.getY();
		}else{
			x = 0;
			y = 0;
		}
		
		
		//move the panel of the body's representation
		container.setLocation(x,y);
	}
}
