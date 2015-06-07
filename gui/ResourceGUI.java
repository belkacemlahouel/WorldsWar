/*
 * Graphic User Interface for resources.
 * The representation of every reource will be different in function of its "type".
 * Version : 1.0
 */
package gui;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import math.MyPoint2D;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;



public class ResourceGUI {
	public JPanelImage container;
	public AbstractResource resource;
	public BufferedImage resourceRepr = null;
	private GUI gui;
	private MyPoint2D coordinates;
	private AbstractEnvironment env;
	
	private static final Random rd = new Random();
	private final float startX;
	private final float startY;
	
	/*
	 * Constructor of the class AgentBodyGUI
	 */
	public ResourceGUI(AbstractResource newResource, GUI gui, MyPoint2D position, AbstractEnvironment envContainer)
	{
		this.resource = newResource;
		this.gui = gui;
		this.coordinates = position;
		this.env = envContainer;
		
		int posX = position.getX();
		int posY = position.getY();
		
		/*startX = rd.nextFloat()%0.75f;
		startY = rd.nextFloat()%0.75f;*/
		startX = 0.0f;
		startY = 0.0f;
		
		int width;
		int height;
		
		switch(resource.getType())
		{
			case ROCK :
				resourceRepr = loadImage("src/res/gui/stone.png");
				break;
			case WOOD:
				resourceRepr = loadImage("src/res/gui/wood.png");
				break;
			case LEAF :
				resourceRepr = loadImage("src/res/gui/leaf.png");
				break;
			case MEAT:
				resourceRepr = loadImage("src/res/gui/meat.png");
				break;
			case SUGAR :
				resourceRepr = loadImage("src/res/gui/sugar.png");
				break;
			case FRUIT:
				resourceRepr = loadImage("src/res/gui/fruit.png");
				break;
			case POISON :
				resourceRepr = loadImage("src/res/gui/poison.png");
				break;
			case GAS:
				resourceRepr = loadImage("src/res/gui/gas.png");
				break;
			default :
				break;
		}
		
		this.container = new JPanelImage(resourceRepr);
		
		/* Use the camera to put the agent body gui at the right place */
		Rectangle rectPos;
		Camera camera = this.gui.getCamera();
		rectPos = camera.logical2absolutePixel(env, posX, posY);
		
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
		
		gui.getWorldObjectPanel().add(container);
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
		int width, height;
		
		int x = getPosXResource();
		int y = getPosYResource();
		Rectangle rectPos;
		Camera camera = this.gui.getCamera();
		
		rectPos = camera.logical2absolutePixel(env, x, y);
		
		//if(rectPos != null)		System.out.println(env + rectPos.toString());
		
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
		
		
		//move the panel of the body's representation
		container.setSize(width, height);	
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
