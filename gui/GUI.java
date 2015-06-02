package gui;

import env2.api.AbstractEnvironment;
import gui.window.SimpleFrame;
import gui.window.Viewport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SpringLayout;

import math.MyMath;

public class GUI extends SimpleFrame
{
	private static final long serialVersionUID = 1355585020854646163L;
	
	/* Attributes */
	private List<AbstractEnvironment> envList;	
	private List<Viewport> vpList;

	private Camera camera;
	private GUIController controller;
	
	private JPanel mainPanel;
	private JPanel antPanel;
	private SpringLayout layout;

	/* Constructors */
	public GUI(List<AbstractEnvironment> envList) {		
		this(envList, 0);	
	}	
	public GUI(List<AbstractEnvironment> envList, int mainViewportID) {	
		/* Basic settings */
		super("GUI Frame"); //$NON-NLS-1$	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		/* Attribute affectation */
		this.envList = envList;	
		this.vpList = new ArrayList<Viewport>();

		int envCount = envList.size();		
		
		/* Set the panels */		
		this.initPanels();
		
		/* Create a camera */
		this.camera = new Camera(this);
		this.camera.setMainViewportID(MyMath.clamp(mainViewportID, 0, envCount));
		
		/* Create the viewport and put it in the main panel */
		Viewport tmpVP;		
		for(int i=0; i<envCount; i++)
		{
			tmpVP = new Viewport(i, this.envList.get(i), this.camera);
			
			this.camera.addViewport(tmpVP, 0, 0);

			this.vpList.add(tmpVP);			
			this.mainPanel.add(tmpVP);
			tmpVP.revalidate();
		}
		
		/* Init the controller and set the layout */
		this.initController();
		this.mainPanelLayoutSetting(true);
	}
	
	private void initPanels()
	{
		/* Init the main panel and set it as a ContentPane */
		this.layout = new SpringLayout();
		
		this.mainPanel = new JPanel();		
		this.mainPanel.setLayout(this.layout);
		
		this.setContentPane(this.mainPanel);
		
		/* Init the ant panel and set it as a GlassPane */
		this.antPanel = new JPanel();
		
		this.antPanel.setOpaque(false); 
        this.antPanel.setLayout(null);
        
		this.setGlassPane(this.antPanel);
		this.getGlassPane().setVisible(true);
	}
	
	/* Getters */
	public List<AbstractEnvironment> getEnvList() {
		return Collections.unmodifiableList(envList);
	}
	
	public JPanel getAntPanel(){
		return this.antPanel;
	}
	public Camera getCamera(){
		return this.camera;
	}
	
	/* Layout functions */
	private void mainPanelLayoutSetting(boolean calledFromConstructor){		
		int envCount = this.envList.size();
		int mainViewportID = this.camera.getMainViewportID();
		
		/* Resize the main panel */
		Dimension screenSize = this.getSize();
		mainPanel.setPreferredSize(screenSize);
		
		/* Compute panels dimensions */
		int padding = 20;
		
		int mainWidth = screenSize.width;
		int mainHeight = screenSize.height/2;
		
		int thumbsWidth;
		int thumbsHeight;
		
		if (envCount-1 > 0) {
			thumbsWidth = (screenSize.width-padding*(envCount-1))/(envCount-1);
			thumbsHeight = screenSize.height/2-padding;
		} else {
			mainHeight = screenSize.height;
			thumbsWidth = screenSize.width;
			thumbsHeight = 0;
		}
		
		/* Reset the layout and resize/scale panels */
		if(envCount > 0)
		{
			Viewport tmpVP, previousVP = null;
			
			/* Add the active VP first */			
			tmpVP = this.vpList.get(mainViewportID);			

			tmpVP.setPreferredSize(new Dimension(mainWidth, mainHeight));
			this.camera.graphicalResize(mainViewportID, 0, 0, mainWidth, mainHeight);
			this.camera.graphicalRescale(mainViewportID, mainWidth, mainHeight, true);

			this.layout.putConstraint(SpringLayout.WEST, tmpVP, 0, SpringLayout.WEST, this.mainPanel);
			this.layout.putConstraint(SpringLayout.NORTH, tmpVP, 0, SpringLayout.NORTH, this.mainPanel);
			
			tmpVP.revalidate();
			
			/* Add the others */
			int currentPosition = 0;
			for(int i=0; i<envCount; i++)
			{
				if(i != mainViewportID)
				{
					tmpVP = this.vpList.get(i);					
					
					tmpVP.setPreferredSize(new Dimension(thumbsWidth, thumbsHeight));
					this.camera.graphicalResize(i, currentPosition*(thumbsWidth+padding), mainHeight+padding, thumbsWidth, thumbsHeight);
					this.camera.graphicalRescale(i, thumbsWidth, thumbsHeight, false);
					
					if(currentPosition == 0)
					{
						this.layout.putConstraint(SpringLayout.WEST, tmpVP, 0, SpringLayout.WEST, this.mainPanel);
						this.layout.putConstraint(SpringLayout.NORTH, tmpVP, mainHeight+padding, SpringLayout.NORTH, this.mainPanel);
					}
					else
					{
						this.layout.putConstraint(SpringLayout.WEST, tmpVP, padding, SpringLayout.EAST, previousVP);
						this.layout.putConstraint(SpringLayout.NORTH, tmpVP, 0, SpringLayout.NORTH, previousVP);
					}
					
					tmpVP.revalidate();		
					previousVP = tmpVP;
					
					currentPosition++;
				}
			}
		}				
	}	

	/* Controller functions */
	private void initController()
	{
		/* Add a GUIController : zoom + translate */
		this.controller = new GUIController(this);
		addKeyListener(this.controller);		
		addComponentListener(this.controller);		
	}
	
	public void translateCamera(int dx, int dy){
		/*this.vpList.get(currentVP).translate(dx, dy);
		this.repaint();*/
	}
	public void zoomCamera(int dz){
		/*this.vpList.get(currentVP).zoom(dz);
		this.repaint();*/
	}
	
	public void refreshLayout(){
		this.mainPanelLayoutSetting(false);
	}
}
