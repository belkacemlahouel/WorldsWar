package gui.window;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import math.MyMath;
import env.Cell;
import env.environment.Environment;

public class Viewport extends JPanel{
	private static final long serialVersionUID = 706657421606903524L;

	/* Constants */
	private static final Color defaultCellBackgroundColor = Color.GREEN;	
	private static final int defaultCellPixelSize = 20;	
	private static final int defaultCellPixelMinSize = 10;	
	private static final int defaultCellPixelMaxSize = 30;	

	private static final int defaultViewportPosX = 0;
	private static final int defaultViewportPosY = 0;
	private static final int defaultViewportHeight = 600;
	private static final int defaultViewportWidth = 800;
	
	private static final int defaultViewportMoveSpeed = 5;
	private static final int defaultViewportZoomSpeed = 1;
	
	/* Attributes */ 
	private Environment env;	
	
	private Color cellBackgroundColor;	
	private int cellPixelSize;	
	
	private int viewportPosX;
	private int viewportPosY;
	private int viewportHeight;
	private int viewportWidth;
	
	/* Constructors */ 
	public Viewport(Environment e) {
		this(e, defaultViewportPosX, defaultViewportPosY, 
				defaultViewportWidth, defaultViewportHeight, 
				defaultCellPixelSize, defaultCellBackgroundColor);
	}	
	public Viewport(Environment e, int x, int y) {		
		this(e, x, y, defaultViewportWidth, defaultViewportHeight, defaultCellPixelSize, defaultCellBackgroundColor);
	}	
	public Viewport(Environment e, int x, int y, int width, int height) {		
		this(e, x, y, width, height, defaultCellPixelSize, defaultCellBackgroundColor);
	}	
	public Viewport(Environment e, int x, int y, int width, int height, int cellPixelSize) {		
		this(e, x, y, width, height, cellPixelSize, defaultCellBackgroundColor);
	}	
	public Viewport(Environment e, int x, int y, int width, int height, int cellPixelSize, Color cellBgColor) {		
		super();				
		assert(e != null);
		
		this.env = e;			
		
		this.cellBackgroundColor = cellBgColor;
		this.cellPixelSize = cellPixelSize;	
		
		this.viewportPosX = Math.max(0, x);
		this.viewportPosY = Math.max(0, y);
		this.viewportHeight = Math.max(1, height);
		this.viewportWidth = Math.max(1, width);
	}	

	/* Moving functions */ 
	public void translate(int dx, int dy){		
		if(dx != 0 || dy != 0)
			this.move(this.viewportPosX + dx*defaultViewportMoveSpeed, this.viewportPosY + dy*defaultViewportMoveSpeed);
	}
	@Override
	public void move(int x, int y){		
		/* Move on X-axis */
		int maxPosX = Math.max(0, this.env.getWidth()*this.cellPixelSize - this.viewportWidth);		
		this.viewportPosX = MyMath.clamp(x, 0, maxPosX);

		/* Move on Y-axis */
		int maxPosY = Math.max(0, this.env.getHeight()*this.cellPixelSize - this.viewportHeight);		
		this.viewportPosY = MyMath.clamp(y, 0, maxPosY);
	}

	/* Scaling functions */ 
	public void zoom(int dz){	
		/* TODO : make the zoom centereed */
		
		int newSize = this.cellPixelSize + dz*defaultViewportZoomSpeed;
		this.cellPixelSize = MyMath.clamp(newSize, defaultCellPixelMinSize, defaultCellPixelMaxSize);
	}
	
	/* Drawing functions */ 
	@Override
	public void paintComponent(Graphics g) {
	     super.paintComponent(g);
	     	     
	     /* Viewport size */
	     int startX = this.viewportPosX;
	     int startY = this.viewportPosY;
	     
	     int endX = startX + this.viewportWidth;
	     int endY = startY + this.viewportHeight;

	     /* Display limits */
	     int startCellX = startX/this.cellPixelSize;
	     int startCellY = startY/this.cellPixelSize;
	     
	     int endCellX = Math.min(this.env.getWidth(), (int) Math.floor((float)endX/this.cellPixelSize));
	     int endCellY = Math.min(this.env.getHeight(), (int) Math.floor((float)endY/this.cellPixelSize));

	     /* Temporary variables */
	     Cell tmpCell;
	     int tmpCellX, tmpCellY;
	     
	     /* Display */
	     for(int i=startCellX; i<endCellX; i++)
	     {
		     for(int j=startCellY; j<endCellY; j++)
		     {
		    	 tmpCell = this.env.getCell(i, j);
		    	 
		    	 /* Compute cell relative position */
		    	 tmpCellX = i*this.cellPixelSize - startX;
		    	 tmpCellY = j*this.cellPixelSize - startY;

		    	 /* Border of the rectangle */
		    	 g.setColor(Color.BLACK); 
			     g.fillRect(tmpCellX, tmpCellY, this.cellPixelSize, this.cellPixelSize);

		    	 /* Background of the rectangle */
		    	 if(!tmpCell.isEmpty())		g.setColor(Color.CYAN); 	
		    	 else						g.setColor(this.cellBackgroundColor); 
		    	 
		    	 if(i == 0 && j == 0)	g.setColor(Color.RED); 
		    	 
		    	 g.fillRect(tmpCellX+1, tmpCellY+1, this.cellPixelSize-1, this.cellPixelSize-1);
		     }
	     }
	}	
}
