package com.S2DGL.engine.utils;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageLoader {

	public static Image loadImage(String imagePath){
		return Toolkit.getDefaultToolkit().getImage(imagePath);
	}
}
