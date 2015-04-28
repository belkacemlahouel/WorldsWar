package gui.window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class SimpleFrame extends JFrame
{
	private static final long serialVersionUID = 3395904057043144807L;
	
	/* Constructors */
	public SimpleFrame() {	
		this("SimpleFrame", 800, 600); //$NON-NLS-1$
	}
	public SimpleFrame(int width, int height) {	
		this("SimpleFrame", width, height); //$NON-NLS-1$
	}	
	public SimpleFrame(String frameName) {	
		this(frameName, 800, 600);
	}
	public SimpleFrame(String frameName, int width, int height) {	
		super(frameName) ;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(width, height));
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
