package com.S2DGL.engine;

import java.awt.Graphics;
import java.awt.Image;

import com.S2DGL.engine.utils.ImageLoader;

public class Instance {

	public int x, y;

	public Sprite sprite = new Sprite();

	
	public Instance(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void tick(){}
	
	public void draw(Graphics g){
		if(sprite.images.get(sprite.spriteIndex) != null){
		drawDefaultSprite(g);
		}
	}

	public void drawDefaultSprite(Graphics g){
		g.drawImage(sprite.images.get(sprite.spriteIndex), x, y, null);
	}

	public void setSprite(String imagePath){
		Image image = ImageLoader.loadImage(imagePath);

		if(!this.sprite.images.contains(image)){
			this.sprite.images.clear();
			this.sprite.spriteIndex = 0;
			this.sprite.images.add(ImageLoader.loadImage(imagePath));
		}
	}

	public void addSprite(String imagePath){
		this.sprite.images.add(ImageLoader.loadImage(imagePath));
	}
	
	public boolean isOutsideView(){
		return x < -GameEngine.getCurrentScene().camera.x-GameEngine.stopRenderDistance || x > -GameEngine.getCurrentScene().camera.x+GameEngine.RENDERSIZE.width+GameEngine.stopRenderDistance || y < -GameEngine.getCurrentScene().camera.y-GameEngine.stopRenderDistance || y > -GameEngine.getCurrentScene().camera.y+GameEngine.RENDERSIZE.height+GameEngine.stopRenderDistance;
	}

}
