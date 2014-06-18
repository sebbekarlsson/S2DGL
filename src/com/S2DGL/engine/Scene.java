package com.S2DGL.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Scene {

	private int WIDTH;
	private int HEIGHT;
	
	private List<Instance> instances = new ArrayList<Instance>();
	
	public Camera camera = new Camera();
	
	public Scene(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
	}
	
	public void tick(){}
	
	public void drawBackground(Graphics g){}
	public void drawStaticBackground(Graphics g){}
	public void draw(Graphics g){}
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
	
	public Dimension getSceneSize(){
		return new Dimension(WIDTH,HEIGHT);
	}
}
