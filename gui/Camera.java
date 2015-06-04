package gui;

import env2.api.AbstractEnvironment;
import gui.window.EnvironmentViewport;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import math.MyMath;

public class Camera 
{
	/* Constants */
	private static final int defaultCellSize = 20;
	
	private static final int defaultPosX = 0;
	private static final int defaultPosY = 0;
	
	private static final float defaultScale = 1.0f;
	private static final float defaultMinScale = 0.05f;
	
	private static final float defaultMoveSpeed = 5.0f;
	private static final float defaultZoomSpeed = 10.0f;
	
	/* Attributes */
	GUI gui;
	
	List<ViewportVisualInfos> vpInfosList;
	private int mainViewportID = 0;
	
	/* Constructors */ 
	public Camera(GUI gui) {
		this.gui = gui;
		
		vpInfosList = new ArrayList<ViewportVisualInfos>();
		mainViewportID = 0;
	}
	
	/* Inner class */
	private class ViewportVisualInfos{
		/* Attributes */	
		/* Cell-related position*/
		private int logicX;
		private int logicY;

		/* Cell-related size (Env size) */
		private int logicWidth;
		private int logicHeight;

		/* Panel-related position */
		private int graphicalX;
		private int graphicalY;

		/* Panel-related size */
		private int graphicalWidth;
		private int graphicalHeight;
		
		private float scale;
		private float minScale;
		
		/* Constructors */
		public ViewportVisualInfos(int logicX, int logicY, int graphicalX, int graphicalY, int width, int height) {
			/* Attributes setting */
			this.logicWidth = Math.max(1, width);
			this.logicHeight = Math.max(1, height);
			
			this.setLogicX(logicX);
			this.setLogicY(logicY);

			this.setGraphicalX(graphicalX);
			this.setGraphicalY(graphicalY);
			
			this.scale = defaultScale;
			this.minScale = defaultMinScale;
		}
		
		/* Setters */
		public void setLogicX(int logicX){
			this.logicX = MyMath.clamp(logicX, 0, this.logicWidth);
		}
		public void setLogicY(int logicY){
			this.logicY = MyMath.clamp(logicY, 0, this.logicHeight);
		}
		
		public void setGraphicalX(int graphicalX){
			this.graphicalX = graphicalX;
		}
		public void setGraphicalY(int graphicalY){
			this.graphicalY = graphicalY;
		}

		public void setGraphicalWidth(int graphicalWidth) {
			this.graphicalWidth = graphicalWidth;
		}
		public void setGraphicalHeight(int graphicalHeight) {
			this.graphicalHeight = graphicalHeight;
		}
		
		public void setScale(float s) {
			this.scale = Math.max(s, this.minScale);
		}
		public void setMinScale(float s) {
			this.minScale = Math.max(0.05f, s);
			this.scale = Math.max(this.scale, s);
		}
	
		/* Getters */
		public int getLogicX() {
			return this.logicX;
		}
		public int getLogicY() {
			return this.logicY;
		}

		public int getGraphicalX() {
			return this.graphicalX;
		}
		public int getGraphicalY() {
			return this.graphicalY;
		}
		
		public int getGraphicalWidth() {
			return graphicalWidth;
		}
		public int getGraphicalHeight() {
			return graphicalHeight;
		}
		
		public int getLogicWidth() {
			return this.logicWidth;
		}
		public int getLogicHeight() {
			return this.logicHeight;
		}
		
		public float getScale() {
			return this.scale;
		}
	}
	
	/* Viewport functions */
	public void addViewport(EnvironmentViewport vp, int graphicalX, int graphicalY) {
		this.addViewport(vp, graphicalX, graphicalY, defaultPosX, defaultPosY);		
	}
	public void addViewport(EnvironmentViewport vp, int graphicalX, int graphicalY, int logicX, int logicY) {		
		/* Attributes setting */
		AbstractEnvironment env = vp.getEnv();
		vpInfosList.add(new ViewportVisualInfos(logicX, logicY, graphicalX, graphicalX, env.getWidth(), env.getHeight()));
	}

	private boolean isViewportIDValid(int vpID){
		int size = vpInfosList.size();
		return (size != 0 && vpID >= 0 && vpID <size);
	}
	
	/* Logic - Graphical functions */
	public Rectangle logical2absolutePixel(AbstractEnvironment e, int i, int j){		
		return this.logical2absolutePixel(findEnvID(e), i, j);
	}
	public Rectangle logical2absolutePixel(int vpID, int i, int j){
		if(!isViewportIDValid(vpID))
			return null;
		
		ViewportVisualInfos vpInfo = vpInfosList.get(vpID);		
		
		int cellSize = (int)(defaultCellSize * vpInfo.getScale());
		
		int relativeGraphicalX = (i-vpInfo.getLogicX())*cellSize;
		int relativeGraphicalY = (j-vpInfo.getLogicY())*cellSize;
		
		if(relativeGraphicalX < 0 || relativeGraphicalX > vpInfo.getGraphicalWidth() || relativeGraphicalY < 0 || relativeGraphicalY > vpInfo.getGraphicalHeight())
			return new Rectangle(-500, -500, 0, 0);		
		
		return new Rectangle(vpInfo.getGraphicalX()+relativeGraphicalX, vpInfo.getGraphicalY()+relativeGraphicalY, cellSize, cellSize);	 
	}

	public Rectangle logical2relativePixel(AbstractEnvironment e, int i, int j){
		int vpID = findEnvID(e);
		
		if(!isViewportIDValid(vpID))
			return null;			
			
		ViewportVisualInfos vpInfo = vpInfosList.get(vpID);		
		int cellSize = (int)(defaultCellSize * vpInfo.getScale());
		
		return new Rectangle(i*cellSize, j*cellSize, cellSize, cellSize);		
	}
	public Rectangle logical2relativePixel(int vpID, int i, int j){
		if(!isViewportIDValid(vpID))
			return null;
					
		ViewportVisualInfos vpInfo = vpInfosList.get(vpID);		
		int cellSize = (int)(defaultCellSize * vpInfo.getScale());
		
		return new Rectangle(i*cellSize, j*cellSize, cellSize, cellSize);		
	}
		
	/* Graphical update functions */
	public void graphicalResize(int vpID, int panelGraphicalX, int panelGraphicalY, int panelGraphicalWidth, int panelGraphicalHeight)
	{
		if(isViewportIDValid(vpID))
		{
			ViewportVisualInfos vpInfo = this.vpInfosList.get(vpID);		
	
			/* Resize */
		    vpInfo.setGraphicalX(panelGraphicalX);	
		    vpInfo.setGraphicalY(panelGraphicalY);	
	
		    vpInfo.setGraphicalWidth(panelGraphicalWidth);	
		    vpInfo.setGraphicalHeight(panelGraphicalHeight);	
		}
	}
	public void graphicalRescale(int vpID, int panelWidth, int panelHeight, boolean isMainVP)
	{
		if(isViewportIDValid(vpID))
		{
			ViewportVisualInfos vpInfo = this.vpInfosList.get(vpID);		
			
			/* Find scaling */
			float scaleX = panelWidth/((float)vpInfo.getLogicWidth()*defaultCellSize);
			float scaleY = panelHeight/((float)vpInfo.getLogicHeight()*defaultCellSize);
			
			float finalScale;
			/*if(isMainVP)	finalScale = Math.max(scaleX, scaleY);
			else			finalScale = Math.min(scaleX, scaleY);*/
			
			finalScale = Math.min(scaleX, scaleY);
			
			/* Scale */
		    vpInfo.setMinScale(finalScale);	
		    vpInfo.setScale(finalScale);	

			/* As the camera is minimized, the camera starts at (0,0) */
		    vpInfo.setLogicX(0);
		    vpInfo.setLogicY(0);
		}
	}

	/* Various functions */	
	public int findEnvID(AbstractEnvironment e)
	{
		int envID = -1;
		List<AbstractEnvironment> envList = gui.getEnvList();
		
		if(envList != null)
		{
			int size = envList.size();
			
			int i=0;
			while(i<size && envID==(-1))
			{
				if(envList.get(i).equals(e)) 	envID = i;
				i++;
			}
		}
		
		return envID;
	}
	
	/* Getters & setters */
	public int getMainViewportID() {
		return mainViewportID;
	}
	public void setMainViewportID(int mainViewportID) {
		if(isViewportIDValid(mainViewportID))
			this.mainViewportID = mainViewportID;
	}
}
