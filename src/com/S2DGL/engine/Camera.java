package com.S2DGL.engine;

public class Camera {

	public int x,y;
	Instance instance;
	private boolean follow = false;
	
	public void tick(){
		if(instance != null && follow){
			x = -instance.x+(GameEngine.RENDERSIZE.width/2)-instance.sprite.images.get(instance.sprite.spriteIndex).getWidth(null)/2;
			y = -instance.y+(GameEngine.RENDERSIZE.height/2)-instance.sprite.images.get(instance.sprite.spriteIndex).getHeight(null)/2;;
		}
	}
	
	public void followInstance(Instance instance){
		this.instance = instance;
		follow = true;
	}
	
	public void stopFollowing(){
		instance = null;
		follow = false;
	}
	
}
