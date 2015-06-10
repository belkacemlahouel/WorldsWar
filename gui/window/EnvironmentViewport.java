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
	private static final Color defaultVPBackgroundColor = new Color(240, 255, 240);
	
	private static final Color defaultCellBackgroundColor = new Color(0, 255, 0);		
	private static final Color portalCellBackgroundColor = new Color(193, 143, 0);	
	
	/* Attributes */ 
	private int id;
	
	private Camera camera;
	private AbstractEnvironment env;
	
	/* Constructors */ 
	public EnvironmentViewport(int viewportID, AbstractEnvironment e, Camera cam) {
		this(viewportID, e, cam, 0, 0);
	}	
	public EnvironmentViewport(int viewportID, AbstractEnvironment e, Camera cam, int x, int y) {		
		super();		
		
		this.id = viewportID;	
		
		this.camera = cam;
		this.env = e;		
	}	

	/* Getters */
	public AbstractEnvironment getEnv() {
		return env;
	}
	
	/* Moving functions */ 

	/* Scaling functions */ 
	
	/* Drawing functions */ 
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(defaultVPBackgroundColor);
		
		/* Get limits */
		int minI = this.camera.getLogicX(this.id);
		int minJ = this.camera.getLogicY(this.id);
		int maxI = this.env.getWidth();
		int maxJ = this.env.getHeight();

		/* Temporary variables */
		AbstractCell tmpCell;
		Rectangle cellRect;

		/* Display */
		for(int i=minI; i<maxI; i++)
		{
			for(int j=minJ; j<maxJ; j++)
			{
				tmpCell = env.getCell(i, j);
				
				/* Compute cell relative position */
				cellRect = this.camera.logical2relativePixel(id, i, j);
				
				/* Border of the rectangle */
				g.setColor(Color.BLACK); 
				g.fillRect(cellRect.x, cellRect.y, cellRect.width, cellRect.height);
				
				/* Background of the rectangle */	
				if(tmpCell.isPortal())
					g.setColor(portalCellBackgroundColor); 
				else
					g.setColor(defaultCellBackgroundColor); 
				g.fillRect(cellRect.x+1, cellRect.y+1, cellRect.width-1, cellRect.height-1);
			}
		}
	}	
}
