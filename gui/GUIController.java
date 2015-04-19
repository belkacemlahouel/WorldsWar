package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIController implements KeyListener 
{
	/* Attributes */
	private GUI gui;
	
	/* Constructors */
	public GUIController(GUI g) {
		this.gui = g;
	}
	
	/* KeyListenerConstructor */
	@Override
	public void keyPressed(KeyEvent arg0) {		
		/* Moving keys */
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)		this.gui.translateCamera(1, 0);
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)		this.gui.translateCamera(-1, 0);		
		if(arg0.getKeyCode() == KeyEvent.VK_UP)			this.gui.translateCamera(0, -1);
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN)		this.gui.translateCamera(0, 1);

		/* Zooming keys */
		if(arg0.getKeyCode() == KeyEvent.VK_PLUS || arg0.getKeyCode() == KeyEvent.VK_ADD)
			this.gui.zoomCamera(1);
		if(arg0.getKeyCode() == KeyEvent.VK_MINUS || arg0.getKeyCode() == KeyEvent.VK_SUBTRACT)
			this.gui.zoomCamera(-1);	
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {	
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {	
	}	
}
