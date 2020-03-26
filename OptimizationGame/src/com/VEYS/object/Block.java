package com.VEYS.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.VEYS.framework.GameObject;
import com.VEYS.framework.Handler;
import com.VEYS.framework.ID;
import com.VEYS.framework.STATE;
import com.VEYS.window.Game;
import com.VEYS.window.SpriteSheet;

public class Block extends GameObject{
	
	private BufferedImage block_Image;
	private Handler handler;
	public Block(int x, int y, ID id ,SpriteSheet ss,Handler handler) {
		
		super(x, y, id);
		falling = false;
		this.handler = handler;
		width = 32;
		height = 32;
		block_Image = ss.grabImage(0, 0, 32, 32);

	}

	@Override
	public void tick() {
		collosion();
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(block_Image, (int) x, (int) y, null);
		
	}
	
	public void collosion(){
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);

			if(temObject.getId() == ID.Bullet){
				if(getBoundsLeft().intersects(temObject.getBounds())){
					velY = 0;
					handler.removeObject(temObject);
					
				}
				if(getBoundsRight().intersects(temObject.getBounds())){
					velX=0;
					handler.removeObject(temObject);
				}
			}
		}
		
	}
	


	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y,width,height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + (width/2) - ((width/2)/2), (int) y, (int) width/2, (int) height / 2);
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width/2) - ((width/2)/2), (int) y + (height / 2),(int) width /2 ,(int) height/2);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 4 , 5, height - 8 );
	}
	public Rectangle getBoundsRight() { 
		
		return new Rectangle((int) x + width -5, (int) y + 4 ,(int) 5, (int) height - 8);
	}

}
