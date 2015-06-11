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
	
	/* Images */
	private static BufferedImage ROCK_IMAGE;
	private static BufferedImage WOOD_IMAGE;
	private static BufferedImage LEAF_IMAGE;
	private static BufferedImage MEAT_IMAGE;
	private static BufferedImage SUGAR_IMAGE;
	private static BufferedImage FRUIT_IMAGE;
	private static BufferedImage POISON_IMAGE;
	private static BufferedImage GAS_IMAGE;
	
	private static boolean hasBeenLoaded = false;
	
	/*
	 * Constructor of the class AgentBodyGUI
	 */
	public ResourceGUI(AbstractResource newResource, GUI gui, MyPoint2D position, AbstractEnvironment envContainer)
	{
		this.loadAllImages();
		
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
				resourceRepr = ROCK_IMAGE;
				break;
			case WOOD:
				resourceRepr = WOOD_IMAGE;
				break;
			case LEAF :
				resourceRepr = LEAF_IMAGE;
				break;
			case MEAT:
				resourceRepr = MEAT_IMAGE;
				break;
			case SUGAR :
				resourceRepr = SUGAR_IMAGE;
				break;
			case FRUIT:
				resourceRepr = FRUIT_IMAGE;
				break;
			case POISON :
				resourceRepr = POISON_IMAGE;
				break;
			case GAS:
				resourceRepr = GAS_IMAGE;
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
	private void loadAllImages()
	{
		if(!hasBeenLoaded)
		{
			ROCK_IMAGE = loadImage("src/res/gui/stone.png");
			WOOD_IMAGE = loadImage("src/res/gui/wood.png");
			LEAF_IMAGE = loadImage("src/res/gui/leaf.png");
			MEAT_IMAGE = loadImage("src/res/gui/meat.png");
			SUGAR_IMAGE = loadImage("src/res/gui/sugar.png");
			FRUIT_IMAGE = loadImage("src/res/gui/fruit.png");
			POISON_IMAGE = loadImage("src/res/gui/poison.png");
			GAS_IMAGE = loadImage("src/res/gui/gas.png");
			
			hasBeenLoaded = true;
		}
	}
}
