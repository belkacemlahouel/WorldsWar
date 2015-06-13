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

import sim.Simulator;
import math.MyMath;
import math.MyPoint2D;
import env2.api.AbstractBody;
import env2.api.AbstractCell;
import env2.api.AbstractEnvironment;
import env2.api.AbstractInfluence;
import env2.api.AbstractResource;
import env2.api.AbstractWorldObject;
import env2.api.InterfaceGatherer;
import env2.api.InterfaceObservable;
import env2.api.InterfaceObserver;
import env2.influences.PutInfluence;
import env2.instanciator.factory.ResourceFactory;
import env2.type.InfluenceType;
import env2.type.WorldObjectType;
import gui.window.EnvironmentViewport;
import gui.window.SimpleFrame;

public class GUI implements InterfaceObserver
{
	/* Constants */
	private final int defaultResourcesQtyAddedOnClick = 10;
	private final WorldObjectType defaultResourcesTypeAddedOnClick = WorldObjectType.FRUIT;
	
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
	
	private List<AbstractResource> resourcesList = new LinkedList<AbstractResource>();
	private List<ResourceGUI> resourcesGUIList = new LinkedList<ResourceGUI>();
	
	private boolean hasEnvBeenInit = false;
	private boolean hasViewportBeenModified = false;

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
		if(!hasEnvBeenInit)
		{
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
			hasViewportBeenModified = true;
			this.refresh(false);			
		}
	}
	public void refreshLayout(){
		this.mainPanelLayoutSetting();
	}	

	/* Agents functions */
	private void createAgentBodyGUI(AbstractBody a)
	{
		if(a != null)
		{
			WorldObjectType type = a.getType();
			
			if(type != null)
			{
				if(WorldObjectType.isAntBody(type))					createAgentBodyGUI("Ant", a);
				else if(WorldObjectType.isTermiteBody(type))		createAgentBodyGUI("Termite", a);
				else if(WorldObjectType.isSpiderBody(type))			createAgentBodyGUI("Spider", a);
			}
		}
	}
	private void createAgentBodyGUI(String species, AbstractBody a)
	{
		if(!species.isEmpty() && species != null && a != null)
		{
			AgentBodyGUI agentBody = new AgentBodyGUI(species, this, a);
			agentBody.move();
			
			this.agentBodyList.add(agentBody);
			this.worldObjectPanel.add(agentBody.container);
			this.hasViewportBeenModified = true;
		}
	}
	private void deleteAgentBodyGUI(AbstractBody a)
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
					
					this.hasViewportBeenModified = true;
				}
			}
		}
	}
	
	public void createResourceGUI(AbstractResource r, MyPoint2D pos, AbstractEnvironment env)
	{
		if(r != null)
		{
			WorldObjectType type = r.getType();
			
			if(type != null)
			{
				if(!WorldObjectType.isBody(type)){
					ResourceGUI resourceGUI = new ResourceGUI(r, this, pos, env);
					resourceGUI.move();

					this.resourcesList.add(r);
					this.resourcesGUIList.add(resourceGUI);
					this.worldObjectPanel.add(resourceGUI.container);

					hasViewportBeenModified = true;
				}
			}
		}
	}
	public void deleteResourceGUI(AbstractResource a)
	{
		if(a != null)
		{
			ResourceGUI tmpResource;
			Iterator<ResourceGUI> resourceIterator = this.resourcesGUIList.iterator();
			
			boolean resourceFound = false;
			
			if(this.resourcesList.remove(a))
			{
				while(resourceIterator.hasNext() && !resourceFound)
				{
					tmpResource = resourceIterator.next();
					if(tmpResource.resource.equals(a))
					{					
						this.worldObjectPanel.remove(tmpResource.container);
						
						resourceIterator.remove();	
						resourceFound = true;
						
						hasViewportBeenModified = true;
					}
				}
			}
		}
	}
	
	private void cleanAllResources(){
		for(ResourceGUI r : resourcesGUIList)
		{
			worldObjectPanel.remove(r.container);
		}
		
		resourcesGUIList.clear();
		resourcesList.clear();		
	}
	private void addNewResources(){
		AbstractCell c;
		WorldObjectType t;
		int i, j, width, height;
		
		this.cleanAllResources();
		
		for(AbstractEnvironment e : this.envList)
		{
			width = e.getWidth();
			height = e.getHeight();
			
			for(i=0; i<width; i++)
			{
	 			for(j=0; j<height; j++)
	 			{
	 				c = e.getCell(i, j);
	 				
	 				for (AbstractWorldObject o : c.getObjects()) 
	 				{
	 					t = o.getType();
						if(!WorldObjectType.isBody(t))
							this.createResourceGUI((AbstractResource) o, new MyPoint2D(i, j), e);
					}
	 			}
			}
		}
	}
	
	/* Controller functions */
	private void initController()
	{
		/* Add a GUIController : zoom + translate */
		this.controller = new GUIController(this);
		frame.addComponentListener(this.controller);	
		worldObjectPanel.addMouseListener(this.controller);	
		frame.addKeyListener(this.controller);		
	}
	
	public void translateCamera(int dx, int dy){
		this.camera.translateMainViewport(dx, dy);
	}
	public void zoomCamera(int dz){
		/*this.vpList.get(currentVP).zoom(dz);
		this.repaint();*/
	}
	
	public void addResouceOnPointer(int pointerX, int pointerY){
		int envID = this.camera.findEnvPointed(pointerX, pointerY);
		if(envID != -1)
		{
			int cellCoord[] = this.camera.findCellPointed(envID, pointerX, pointerY);			
			if(cellCoord[0] != -1 && cellCoord[1] != -1)
			{
				AbstractEnvironment e = envList.get(envID);
				int x = cellCoord[0], y = cellCoord[1];
				
//				/* Get Gatherer and move him */
//				InterfaceGatherer gatherer = Simulator.getVirtualGatherer();
//				gatherer.move(e, new MyPoint2D(x, y));
//
//				/* Create resource */
//				AbstractResource res = ResourceFactory.RESOURCE_INSTANCIATOR.get(defaultResourcesTypeAddedOnClick).getNew();
//				res.add(defaultResourcesQtyAddedOnClick);
//
//				/* Add resource influence*/	
//				e.getCell(x, y).addInfluence((AbstractInfluence) new PutInfluence(gatherer, res, 1));
				
				AbstractCell cell = e.getCell(x, y);
				
				Simulator.setPointedCell(cell);
				AbstractResource res = ResourceFactory.RESOURCE_INSTANCIATOR.get(defaultResourcesTypeAddedOnClick).getNew();
				cell.addInfluence(new PutInfluence(null, res, defaultResourcesQtyAddedOnClick));
				
				Simulator.getInstance().addInfluencedByGUICell(cell);
			}
		}
	}
	public void setMainViewportID(int mainViewportID) {
		if(this.camera.setMainViewportID(mainViewportID))
			this.refreshLayout();
	}
		
	/* Repaint function */
	public void refresh(boolean lookForResources)
    {      
      if(lookForResources){
        this.addNewResources();   
      }
      
      if(this.hasViewportBeenModified)
      {
       this.worldObjectPanel.revalidate(); 
       this.hasViewportBeenModified = false;
      }

     this.moveAllObjects();
     this.worldObjectPanel.repaint();
    }
	
	private void moveAllObjects()
	{
		for(AgentBodyGUI a : agentBodyList)
		{
			a.move();		
		}
		
		if(this.hasViewportBeenModified)	
		{
			for(ResourceGUI r : resourcesGUIList)
				r.move();		
		}
	}
	
	/* Observer interface function */
    public void eventFired(InterfaceObservable caller, int eventCode)
    {
    	if(caller != null)
    	{
    		/* Agent created */
    		if(eventCode == 10)
    		{
	    		createAgentBodyGUI((AbstractBody) caller);
    		}
    		
    		/* Undertaker wants to take his due */
    		if(eventCode == 20)
    		{
	    		deleteAgentBodyGUI((AbstractBody) caller);
    		}
    	}
    }
}
