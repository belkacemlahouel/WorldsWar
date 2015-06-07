package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractEnvironment;
import env2.api.AbstractResource;
import env2.api.InterfaceObservable;
import env2.api.InterfaceObserver;
import env2.type.WorldObjectType;
import gui.window.EnvironmentViewport;
import gui.window.SimpleFrame;

public class GUI implements InterfaceObserver
{
	/* Attributes */
	private SimpleFrame frame;
	
	private List<AbstractEnvironment> envList = null;	
	private List<EnvironmentViewport> vpList = null;

	private Camera camera = null;
	private GUIController controller = null;
	
	private JPanel mapPanel = new JPanel();
	private JPanel worldObjectPanel = new JPanel();
	private SpringLayout layout = null;
	
	private List<AgentBodyGUI> agentBodyList = new LinkedList<AgentBodyGUI>();
	private List<ResourceGUI> resourcesList = new LinkedList<ResourceGUI>();
	
	private boolean hasEnvBeenInit = false;

	/* Constructors */
	private GUI()
	{		
		/* Basic settings */
		frame = new SimpleFrame("GUI Frame"); //$NON-NLS-1$	
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		/* Create a camera */
		this.camera = new Camera(this);
		
		/* Init the controller */
		this.initController();
	}
	
	/* Holder */
	private static class GUIHolder
	{		
		private final static GUI instance = new GUI();
	}
 
	/* Getter */
	public static GUI getInstance()
	{
		return GUIHolder.instance;
	}
	
	/* Initialization functions */
	public void setEnvironmentList(List<AbstractEnvironment> envList) {	
		this.setEnvironmentList(envList, 0);	
	}	
	public void setEnvironmentList(List<AbstractEnvironment> envList, int mainViewportID) {		
		/* Attribute affectation */
		this.envList = envList;	
		this.vpList = new ArrayList<EnvironmentViewport>();

		int envCount = envList.size();		
		
		/* Set the panels */		
		this.initPanels();		
		this.camera.setMainViewportID(MyMath.clamp(mainViewportID, 0, envCount));
		
		/* Create a viewport for each env and put it in the env panel */
		EnvironmentViewport tmpVP;		
		for(int i=0; i<envCount; i++)
		{
			tmpVP = new EnvironmentViewport(i, this.envList.get(i), this.camera);
			
			this.camera.addViewport(tmpVP, 0, 0);

			this.vpList.add(tmpVP);			
			this.mapPanel.add(tmpVP);
			tmpVP.revalidate();
		}
		
		/* Set the layout */
		hasEnvBeenInit = true;
		this.mainPanelLayoutSetting();
	}
	
	private void initPanels()
	{
		/* Init the env panel and set it as a ContentPane */
		this.layout = new SpringLayout();		
		this.mapPanel.setLayout(this.layout);
		
		this.frame.setContentPane(this.mapPanel);
		
		/* Init the ant panel and set it as a GlassPane */		
		this.worldObjectPanel.setOpaque(false); 
        this.worldObjectPanel.setLayout(null);
        
		this.frame.setGlassPane(this.worldObjectPanel);
		this.frame.getGlassPane().setVisible(true);
	}
		
	/* Getters */
	public List<AbstractEnvironment> getEnvList() {
		if(envList != null)
			return Collections.unmodifiableList(envList);
		else
			return null;
	}
	
	public JPanel getWorldObjectPanel(){
		return this.worldObjectPanel;
	}
	public Camera getCamera(){
		return this.camera;
	}
	public SimpleFrame getFrame(){
		return this.frame;
	}
	
	/* Layout functions */
	private void mainPanelLayoutSetting(){				
		if(this.hasEnvBeenInit)
		{
			/* Resize the main panel */
			Dimension screenSize = this.frame.getSize();
			this.mapPanel.setPreferredSize(screenSize);

			int envCount = this.envList.size();
			int mainViewportID = this.camera.getMainViewportID();
			
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
				EnvironmentViewport tmpVP, previousVP = null;
				
				/* Add the active VP first */			
				tmpVP = this.vpList.get(mainViewportID);			
	
				tmpVP.setPreferredSize(new Dimension(mainWidth, mainHeight));
				this.camera.graphicalResize(mainViewportID, 0, 0, mainWidth, mainHeight);
				this.camera.graphicalRescale(mainViewportID, mainWidth, mainHeight, true);
	
				this.layout.putConstraint(SpringLayout.WEST, tmpVP, 0, SpringLayout.WEST, this.mapPanel);
				this.layout.putConstraint(SpringLayout.NORTH, tmpVP, 0, SpringLayout.NORTH, this.mapPanel);
				
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
							this.layout.putConstraint(SpringLayout.WEST, tmpVP, 0, SpringLayout.WEST, this.mapPanel);
							this.layout.putConstraint(SpringLayout.NORTH, tmpVP, mainHeight+padding, SpringLayout.NORTH, this.mapPanel);
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
			this.refresh();			
		}
	}	

	/* Agents functions */
	public void createAgentBody(AbstractBody a)
	{
		if(a != null)
		{
			WorldObjectType type = a.getType();
			
			if(type != null)
			{
				if(WorldObjectType.isAntBody(type))					createAgentBody("Ant", a);
				else if(WorldObjectType.isTermiteBody(type))		createAgentBody("Termite", a);
				else if(WorldObjectType.isSpiderBody(type))			createAgentBody("Spider", a);
			}
		}
	}
	public void createAgentBody(String species, AbstractBody a)
	{
		if(!species.isEmpty() && species != null && a != null)
		{
			AgentBodyGUI agentBody = new AgentBodyGUI(species, this, a);
			agentBody.move();

			this.agentBodyList.add(agentBody);
			this.worldObjectPanel.add(agentBody.container);
		}
	}
	public void deleteAgentBody(AbstractBody a)
	{
		if(a != null)
		{
			AgentBodyGUI tmpBody;
			Iterator<AgentBodyGUI> agentBodyIterator = this.agentBodyList.iterator();
			
			boolean agentBodyFound = false;
			
			while(agentBodyIterator.hasNext() && !agentBodyFound)
			{
				tmpBody = agentBodyIterator.next();
				if(tmpBody.agentBody.equals(a))
				{					
					this.worldObjectPanel.remove(tmpBody.container);
					
					agentBodyIterator.remove();	
					agentBodyFound = true;
				}
			}
		}
	}
	
	public void createRessourceGUI(AbstractResource r, MyPoint2D pos, AbstractEnvironment env)
	{
		if(r != null)
		{
			WorldObjectType type = r.getType();
			
			if(type != null)
			{
				if(!WorldObjectType.isBody(type)){
					ResourceGUI resourceGUI = new ResourceGUI(r, this, pos, env);
					resourceGUI.move();

					this.resourcesList.add(resourceGUI);
					this.worldObjectPanel.add(resourceGUI.container);
				}
			}
		}
	}
	public void deleteRessourceGUI(AbstractResource a)
	{
		if(a != null)
		{
			ResourceGUI tmpResource;
			Iterator<ResourceGUI> resourceIterator = this.resourcesList.iterator();
			
			boolean resourceFound = false;
			
			while(resourceIterator.hasNext() && !resourceFound)
			{
				tmpResource = resourceIterator.next();
				if(tmpResource.resource.equals(a))
				{					
					this.worldObjectPanel.remove(tmpResource.container);
					
					resourceIterator.remove();	
					resourceFound = true;
				}
			}
		}
	}
		
	/* Controller functions */
	private void initController()
	{
		/* Add a GUIController : zoom + translate */
		this.controller = new GUIController(this);
		frame.addKeyListener(this.controller);		
		frame.addComponentListener(this.controller);		
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
		this.mainPanelLayoutSetting();
	}

	/* Repaint function */
	public void refresh()
	{
		this.moveAllObjects();	
		this.worldObjectPanel.revalidate();
		this.worldObjectPanel.repaint();
	}
	private void moveAllObjects()
	{
		for(AgentBodyGUI a : agentBodyList)
			a.move();
		for(ResourceGUI r : resourcesList)
			r.move();
	}
	
	/* Observer interface function */
    public void eventFired(InterfaceObservable caller, int eventCode)
    {
    	if(caller != null)
    	{
    		/* Agent created */
    		if(eventCode == 10)
    		{
	    		createAgentBody((AbstractBody) caller);
    		}
    		
    		/* Undertaker wants to take his due */
    		if(eventCode == 20)
    		{
	    		deleteAgentBody((AbstractBody) caller);
    		}
    	}
    }
}
