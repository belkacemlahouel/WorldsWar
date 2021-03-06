package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class JPanelImage extends JPanel {
	private static final long serialVersionUID = 4641683667519659647L;
	
	private BufferedImage img = null;
    
	/* Constructor */
    public JPanelImage(BufferedImage image) {
		this.img = image;
	}

    /* Setter */
    public void setImage(BufferedImage newImage){
    	this.img = newImage;
    }

    /* Core function */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(this.img != null){
        	g.drawImage(img, 0, 0, null);
        	//g.drawImage(img,0, 0, getWidth(), getHeight(), this);
        }
    }
}
