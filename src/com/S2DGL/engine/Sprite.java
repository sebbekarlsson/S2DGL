package com.S2DGL.engine;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Sprite {

	public List<Image> images = new ArrayList<Image>();
	public int spriteIndex = 0;
	
	public void next(){
		if(spriteIndex < images.size()){
			spriteIndex += 1;
		}else{
			spriteIndex = 0;
		}
	}
	
	public void previous(){
		if(spriteIndex > 0){
			spriteIndex -= 1;
		}else{
			spriteIndex = images.size()-1;
		}
	}
}
