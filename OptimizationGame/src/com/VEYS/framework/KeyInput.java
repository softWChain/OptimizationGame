package com.VEYS.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.VEYS.object.Bullet;
import com.VEYS.object.Player;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	public static boolean valueESC = false;
	public static boolean bullet = false;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_D){
					handler.setRight(true);
				}
				if(key == KeyEvent.VK_A){
					handler.setLeft(true);
				}
				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()){
					tempObject.jumping = true;
					tempObject.setVelY(-13);
				}
				if(key == KeyEvent.VK_ESCAPE){
					setValueESC(true);
				}
				if(key == KeyEvent.VK_F){
					bullet = true;
					handler.addObject(new Bullet((int) tempObject.getX() -5 , (int) tempObject.getY() +10, ID.Bullet, handler));
				}

			}

		}
		
	
		
	}
	
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_D){
					handler.setRight(false);
				}
				if(key == KeyEvent.VK_A){
					handler.setLeft(false);
				}
				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()){
					tempObject.jumping = false;
					tempObject.setVelY(0);
				}
			}
		}
		
	}

	public boolean isValueESC() {
		return valueESC;
	}

	public void setValueESC(boolean valueESC) {
		this.valueESC = valueESC;
	}
	

}
