package com.S2DGL.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameEngine extends JFrame implements Runnable, KeyListener, MouseListener {
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 640;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 2;

	public static final Dimension FRAMESIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static final Dimension RENDERSIZE = new Dimension(FRAMESIZE.width / 2, FRAMESIZE.height / 2);

	private BufferedImage offscreen = new BufferedImage(RENDERSIZE.width, RENDERSIZE.height, BufferedImage.TYPE_INT_RGB);

	private Thread gameLoop = new Thread(this,"Game loop");

	private static List<Scene> scenes = new ArrayList<Scene>();
	private static int sceneIndex = 0;




	public GameEngine(){

		this.setVisible(true);
		this.setSize(FRAMESIZE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addKeyListener(this);
		this.addMouseListener(this);
	}

	public void insertScene(Scene scene){
		scenes.add(scene);
	}

	public void start(){
		if(scenes.size() > 0){
			gameLoop.start();
		}
	}

	public void tick(){
		
		getCurrentScene().tick();

		for(int i = 0; i < getCurrentScene().getInstances().size(); i++){
			Instance instance = getCurrentScene().getInstances().get(i);
			instance.tick();
		}
	}

	public void run() {
		while(true){

			tick();

			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	public void paint(Graphics g){
		if(scenes.size() > 0){
			Graphics GUI = offscreen.getGraphics();
			Graphics g2 = offscreen.getGraphics();
			g2.clearRect(0, 0, RENDERSIZE.width, RENDERSIZE.height);

			getCurrentScene().drawBackground(g2);

			for(int i = 0; i < getCurrentScene().getInstances().size(); i++){
				Instance instance = getCurrentScene().getInstances().get(i);
				instance.draw(g2);
			}

			getCurrentScene().drawGUI(GUI);

			g.drawImage(offscreen.getScaledInstance(FRAMESIZE.width, FRAMESIZE.height, 1), 0, 0, this);
		}
	}


	public static Scene getCurrentScene(){
		if(scenes != null && scenes.size() > 0){

			return scenes.get(sceneIndex);

		}else{

			JOptionPane.showMessageDialog(null, "Could not start the game since there isnt any scenes!");
			return null;

		}


	}
	
	public static void gotoNextScene(){
		if(sceneIndex < scenes.size()){
			sceneIndex += 1;
		}else{
			System.out.println("Cant move to the next scene after the last scene!");
		}
	}
	
	public static void gotoPreviousScene(){
		if(sceneIndex > 0){
			sceneIndex -= 1;
		}else{
			System.out.println("Cant move to the previous scene when on the first scene!");
		}
	}


	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}




	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}




	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}




	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}




	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}




	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}




	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}




	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}







}
