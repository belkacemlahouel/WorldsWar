package gui;

import env.environment.Environment;
import gui.window.SimpleFrame;
import gui.window.Viewport;

public class GUI extends SimpleFrame
{
	private static final long serialVersionUID = 1355585020854646163L;

	/* Constants */
	public static final int defaultFrameWidth = 800; 
	public static final int defaultFrameHeight = 600;
	
	/* Attributes */
	private Environment env;
	private Viewport viewport;

	private GUIController controller;

	/* Constructors */
	public GUI(Environment e) {		
		this(e, defaultFrameWidth, defaultFrameHeight);	
	}	
	public GUI(Environment e, int frameWidth, int frameHeight) {	
		super("GUI Frame", frameWidth, frameHeight); //$NON-NLS-1$
		
		this.env = e;	
		this.viewport = new Viewport(this.env);	
		this.setContentPane(this.viewport);
		
		this.controller = new GUIController(this);
		addKeyListener(this.controller);
	}

	/* Viewport function */
	public void translateCamera(int dx, int dy){
		this.viewport.translate(dx, dy);
		this.repaint();
	}
	public void zoomCamera(int dz){
		this.viewport.zoom(dz);
		this.repaint();
	}
}
