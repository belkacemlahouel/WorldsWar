package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIController implements ComponentListener, KeyListener, MouseListener
{
	/* Attributes */
	private GUI gui;
	
	/* Constructors */
	public GUIController(GUI g) {
		this.gui = g;
	}

	/* ComponentListener functions */
	@Override
	public void componentHidden(ComponentEvent e) {
	}
	@Override
	public void componentMoved(ComponentEvent e) {
	}
	@Override
	public void componentResized(ComponentEvent e) {
		this.gui.refreshLayout();		
	}
	@Override
	public void componentShown(ComponentEvent e) {
	}	
	
	/* KeyListener functions */
	@Override
	public void keyPressed(KeyEvent arg0) {		
		int keyCode = arg0.getKeyCode();
		
		/* Viewport keys */
		if(KeyEvent.VK_0 <= keyCode && keyCode <= KeyEvent.VK_9)		
			this.gui.setMainViewportID(keyCode-KeyEvent.VK_0);		
		
		/* Moving keys */
		if(keyCode == KeyEvent.VK_RIGHT)		this.gui.translateCamera(1, 0);
		if(keyCode == KeyEvent.VK_LEFT)			this.gui.translateCamera(-1, 0);		
		if(keyCode == KeyEvent.VK_UP)			this.gui.translateCamera(0, -1);
		if(keyCode == KeyEvent.VK_DOWN)			this.gui.translateCamera(0, 1);

		/* Zooming keys */
		if(keyCode == KeyEvent.VK_PLUS || keyCode == KeyEvent.VK_ADD)
			this.gui.zoomCamera(1);
		if(keyCode == KeyEvent.VK_MINUS || keyCode == KeyEvent.VK_SUBTRACT)
			this.gui.zoomCamera(-1);	
	}
	@Override
	public void keyReleased(KeyEvent arg0) {	
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
	}

	/* MouseListener functions */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2)
			this.gui.addResouceOnPointer(e.getX(), e.getY());		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
