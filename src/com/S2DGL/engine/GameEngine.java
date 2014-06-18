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
	
	public static int stopRenderDistance = 64;
	
	public static boolean onlyRenderViewable = true;
	
	
	
	public static boolean vk_q = false;
	public static boolean vk_w = false;
	public static boolean vk_e = false;
	public static boolean vk_r = false;
	public static boolean vk_t = false;
	public static boolean vk_y = false;
	public static boolean vk_u = false;
	public static boolean vk_i = false;
	public static boolean vk_o = false;
	public static boolean vk_p = false;
	public static boolean vk_a = false;
	public static boolean vk_s = false;
	public static boolean vk_d = false;
	public static boolean vk_f = false;
	public static boolean vk_g = false;
	public static boolean vk_h = false;
	public static boolean vk_j = false;
	public static boolean vk_k = false;
	public static boolean vk_l = false;
	public static boolean vk_z = false;
	public static boolean vk_x = false;
	public static boolean vk_c = false;
	public static boolean vk_v = false;
	public static boolean vk_b = false;
	public static boolean vk_n = false;
	public static boolean vk_m = false;
	public static boolean vk_shift = false;
	public static boolean vk_ctrl = false;
	public static boolean vk_alt = false;
	public static boolean vk_enter = false;
	public static boolean vk_delete = false;
	public static boolean vk_backspace = false;
	public static boolean vk_up = false;
	public static boolean vk_left = false;
	public static boolean vk_right = false;
	public static boolean vk_down = false;
	public static boolean vk_1 = false;
	public static boolean vk_2 = false;
	public static boolean vk_3 = false;
	public static boolean vk_4 = false;
	public static boolean vk_5 = false;
	public static boolean vk_6 = false;
	public static boolean vk_7 = false;
	public static boolean vk_8 = false;
	public static boolean vk_9 = false;
	public static boolean vk_0 = false;
	




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
		}else{
			JOptionPane.showMessageDialog(null, "Could not start the game because there are no scenes!");
		}
	}

	public void tick(){

		getCurrentScene().tick();
		getCurrentScene().camera.tick();

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
			g2.clearRect(0, 0, FRAMESIZE.width, FRAMESIZE.height);

			g2.translate(getCurrentScene().camera.x, getCurrentScene().camera.y);

			getCurrentScene().drawStaticBackground(GUI);
			getCurrentScene().drawBackground(g2);

			for(int i = 0; i < getCurrentScene().getInstances().size(); i++){
				Instance instance = getCurrentScene().getInstances().get(i);
				if(onlyRenderViewable){
					if(!instance.isOutsideView()){
						instance.draw(g2);
					}
				}else{
					instance.draw(g2);
				}
			}

			g2.translate(-getCurrentScene().camera.x, -getCurrentScene().camera.y);

			
			getCurrentScene().drawGUI(GUI);



			g.drawImage(offscreen.getScaledInstance(FRAMESIZE.width, FRAMESIZE.height, 0), 0, 0, this);
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
		if(e.getKeyCode() == KeyEvent.VK_Q){
			vk_q = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			vk_w = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			vk_e = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_R){
			vk_r = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_T){
			vk_t = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_Y){
			vk_y = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_U){
			vk_u = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_I){
			vk_i = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_O){
			vk_o = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			vk_p = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			vk_a = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			vk_s = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			vk_d = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F){
			vk_f = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_G){
			vk_g = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_H){
			vk_h = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_J){
			vk_j = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_K){
			vk_k = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_L){
			vk_l = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z){
			vk_z = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_X){
			vk_x = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_C){
			vk_c = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_V){
			vk_v = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_B){
			vk_b = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_N){
			vk_n = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_M){
			vk_m = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_CONTROL){
			vk_ctrl = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			vk_shift = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			vk_enter = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			vk_backspace = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DELETE){
			vk_delete = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			vk_down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			vk_up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			vk_left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			vk_right = true;
		}
		
		

	}




	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_Q){
			vk_q = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			vk_w = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			vk_e = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_R){
			vk_r = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_T){
			vk_t = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_Y){
			vk_y = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_U){
			vk_u = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_I){
			vk_i = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_O){
			vk_o = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			vk_p = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			vk_a = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			vk_s = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			vk_d = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F){
			vk_f = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_G){
			vk_g = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_H){
			vk_h = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_J){
			vk_j = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_K){
			vk_k = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_L){
			vk_l = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z){
			vk_z = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_X){
			vk_x = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_C){
			vk_c = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_V){
			vk_v = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_B){
			vk_b = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_N){
			vk_n = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_M){
			vk_m = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_CONTROL){
			vk_ctrl = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			vk_shift = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			vk_enter = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			vk_backspace = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DELETE){
			vk_delete = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			vk_down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			vk_up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			vk_left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			vk_right = false;
		}
		

	}







}
