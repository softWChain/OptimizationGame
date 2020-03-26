package com.VEYS.framework;

import java.awt.Graphics;

import com.VEYS.window.Game;

public class Camera {
	
	protected float x,y;
	
	public Camera(int x , int y){
		
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player){
		
		x = -player.getX() + Game.WIDTH/2;
		y = -player.getY() + Game.HEIGHT/3;
		
	}
	
	public void render(Graphics g){
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	

}
