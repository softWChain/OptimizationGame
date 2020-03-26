package com.VEYS.object;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.VEYS.framework.GameObject;
import com.VEYS.framework.Handler;
import com.VEYS.framework.ID;
import com.VEYS.window.Animation;
import com.VEYS.window.Score;
import com.VEYS.window.SpriteSheet;

public class Player extends GameObject{
	
	private Handler handler;
	
	private BufferedImage img;
	
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10f;
	
	private BufferedImage[] player_Images = new BufferedImage[7];
	private Animation animRight,animLeft;
	private Score score;
	public static boolean jumpHelp = false;
	

	public Player(int x, int y, ID id,Handler handler,Score score,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		this.score = score;
		width = 32;
		height = 34;
		//img  = ss.grabImage(2, 2, 30, 32);
		
		player_Images[0] = ss.grabImage(48, 2, 32, 34);
		player_Images[1] = ss.grabImage(48, 36, 32, 34);
		player_Images[2] = ss.grabImage(2, 36, 32, 34);
		player_Images[3] = ss.grabImage(92, 36, 32, 34);
		player_Images[4] = ss.grabImage(45, 108, 32, 34);
		player_Images[5] = ss.grabImage(0, 107, 32, 34);
		player_Images[6] = ss.grabImage(91, 108, 32, 34);
		
		animRight = new Animation(8, player_Images[1] , player_Images[2], player_Images[3]);
		animLeft = new Animation(8, player_Images[4] , player_Images[5], player_Images[6]);


		
	}

	@Override
	public void tick() {
		
		
		x += velX;
		y += velY;

		if(falling || jumping){
			velY += gravity;
		}
		if(velY > MAX_SPEED){
			velY = MAX_SPEED;
		}
		
		collosion();
		animRight.runAnimation();
		animLeft.runAnimation();
		
		if(handler.isRight()){
			velX = 5;
		}else if(!handler.isLeft()){
			velX = 0;
		}
		if(handler.isLeft()){
			velX = -5;
		}else if(!handler.isRight()){
			velX = 0;
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		if(velX > 0){
			animRight.drawAnimation(g, x, y, 0);
		}else if(velX < 0){
			animLeft.drawAnimation(g, x, y, 0);
		}else{
			g.drawImage(player_Images[0],(int) x,(int) y, null);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}
	
	
	public void collosion(){
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);
			if(temObject.getId() == ID.Block){
				
				if(getBoundsTop().intersects(temObject.getBounds())){
					
					y = temObject.getY() + temObject.getHeight();
					velY = 0;
				}
				
				if(getBoundsBottom().intersects(temObject.getBounds())){
					y = temObject.getY() - height;
					velY=0;
					falling= false;
					jumping = false;
					jumpHelp = true;
				}else{
					falling = true;
				}
				
				if(getBoundsLeft().intersects(temObject.getBounds())){
					x = temObject.getX() + temObject.getWidth();
					
				}
				if(getBoundsRight().intersects(temObject.getBounds())){
					x = temObject.getX() - temObject.getWidth();
				}
			}
			
			if(temObject.getId() == ID.Coin){
				if(getBounds().intersects(temObject.getBounds())) {
					handler.removeObject(temObject);
					score.setPoint(score.getPoint() + 1);
					
				}
			}
			if(temObject.getId() == ID.Enemy){
				if(getBounds().intersects(temObject.getBounds())){
					score.setHealt(score.getHealt() - 0.1f);
				}
			}

		}
		
	
	}

	@Override
	public Rectangle getBounds() { 
		
		return new Rectangle((int) x , (int) y, width,height);
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
