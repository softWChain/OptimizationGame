package com.VEYS.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.VEYS.framework.GameObject;
import com.VEYS.framework.Handler;
import com.VEYS.framework.ID;
import com.VEYS.framework.KeyInput;
import com.VEYS.window.BufferedImageLoader;
import com.VEYS.window.Game;

public class Bullet extends GameObject{
	
	private BufferedImage img;
	private Handler handler;
	private BufferedImageLoader loader = new BufferedImageLoader();
	public Bullet(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		width = 25;
		height =13;
		img = loader.loadImage("/newBullet2.png");
		
		
	}

	@Override
	public void tick() {
		
		x += 8;
		collosion();
		
		
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.LIGHT_GRAY);
		if(KeyInput.bullet == true){
			g.drawImage(img, (int) x, (int) y, null);
		}

	}
	
	public void collosion(){
		
		for(int i =0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);
			

			
			if(temObject.getId() == ID.Enemy){
				if(getBounds().intersects(temObject.getBounds())){
					Enemy.gifAnim = true;
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
