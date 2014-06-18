package com.S2DGL.engine;



import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.SwingUtilities;

public class Mouse {

	public static Point getMousePositionInScene(GameEngine engine){
		int x,y;
		PointerInfo a = MouseInfo.getPointerInfo();
		Point point = new Point(a.getLocation());
		SwingUtilities.convertPointFromScreen(point, engine.getComponent(0));
		x=(int) (point.getX()/2)-(GameEngine.getCurrentScene().camera.x);
		y=(int) (point.getY()/2)-(GameEngine.getCurrentScene().camera.y);
		 
		
		return new Point(x,y);
	}
	
	public static Point getMousePositionInGUI(GameEngine engine){
		int x,y;
		PointerInfo a = MouseInfo.getPointerInfo();
		Point point = new Point(a.getLocation());
		
		SwingUtilities.convertPointFromScreen(point, engine.getComponent(0));
		x=(int) (point.getX()/2);
		y=(int) (point.getY()/2);
		 
		
		return new Point(x,y);
	}
}
