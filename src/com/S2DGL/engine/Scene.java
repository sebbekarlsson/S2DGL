package com.S2DGL.engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Scene {

	private List<Instance> instances = new ArrayList<Instance>();
	
	public void tick(){}
	
	public void drawBackground(Graphics g){}
	public void drawGUI(Graphics g){}
	
	public void instantiate(Instance instance){
		this.instances.add(instance);
	}
	
	public void destroy(Instance instance){
		this.instances.remove(instance);
	}
	
	public void clearInstances(){
		this.instances.clear();
	}
	
	public int countInstances(){
		return instances.size();
	}
	
	public List<Instance> getInstances(){
		return this.instances;
	}
}
