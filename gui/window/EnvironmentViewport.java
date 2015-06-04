package gui.window;

import env2.api.AbstractCell;
import env2.api.AbstractEnvironment;
import gui.Camera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class EnvironmentViewport extends JPanel
{
	private static final long serialVersionUID = 706657421606903524L;

	/* Constants */
	private static final Color defaultCellBackgroundColor = Color.GREEN;	
	
	/* Attributes */ 
	private int id;
	
	private Camera camera;
	private AbstractEnvironment env;
	
	private Color cellBgColor;	
	
	/* Constructors */ 
	public EnvironmentViewport(int viewportID, AbstractEnvironment e, Camera cam) {
		this(viewportID, e, cam, 0, 0, defaultCellBackgroundColor);
	}	
	public EnvironmentViewport(int viewportID, AbstractEnvironment e, Camera cam, int x, int y) {		
		this(viewportID, e, cam, x, y, defaultCellBackgroundColor);
	}	
	public EnvironmentViewport(int viewportID, AbstractEnvironment e, Camera cam, int x, int y, Color cellBgColor) {		
		super();		
		
		this.id = viewportID;	
		
		this.camera = cam;
		this.env = e;		
		
		this.cellBgColor = cellBgColor;		
	}	

	/* Getters */
	public AbstractEnvironment getEnv() {
		return env;
	}
	
	/* Moving functions */ 

	/* Scaling functions */ 
	
	/* Drawing functions */ 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.PINK);
		
		/* Get limits */
		int maxI = this.env.getWidth();
		int maxJ = this.env.getHeight();

		/* Temporary variables */
		AbstractCell tmpCell;
		Rectangle cellRect;

		/* Display */
		/* TODO : remove grid for optimization later */
		for(int i=0; i<maxI; i++)
		{
			for(int j=0; j<maxJ; j++)
			{
				tmpCell = this.env.getCell(i, j);

				/* Compute cell relative position */
				cellRect = this.camera.logical2relativePixel(id, i, j);
				
				/* Border of the rectangle */
				g.setColor(Color.BLACK); 
				g.fillRect(cellRect.x, cellRect.y, cellRect.width, cellRect.height);
				
				/* Background of the rectangle */	
				g.setColor(this.cellBgColor); 
				g.fillRect(cellRect.x+1, cellRect.y+1, cellRect.width-1, cellRect.height-1);
			}
		}
	}	
}
