package com.VEYS.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.VEYS.framework.GameObject;
import com.VEYS.framework.Handler;
import com.VEYS.framework.ID;
import com.VEYS.window.Animation;
import com.VEYS.window.Explosion;
import com.VEYS.window.Game;
import com.VEYS.window.SpriteSheet;

public class Enemy extends GameObject{
	private Handler handler;
	private float  distance,diffX,diffY;
	
	private Animation animForward;
	private Animation animBackward;
	private Animation animLeft;
	private Animation animRight;
	public static boolean gifAnim= false;
	public static int tempX,tempY;
	private Explosion explosion;

	private BufferedImage[] enemy_image = new BufferedImage[12];

	public Enemy(int x, int y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		falling = false;
		width = 25;
		height = 25;
		/*//FORWARD
		enemy_image[0] = ss.grabImage(8, 68, 32, 34);
		enemy_image[1] = ss.grabImage(35, 70, 32, 34);
		enemy_image[2] = ss.grabImage(69, 67, 32, 34);
		//RİGHT
		enemy_image[3] = ss.grabImage(7, 97, 32, 34);
		enemy_image[4] = ss.grabImage(37, 100, 32, 34);
		enemy_image[5] = ss.grabImage(69, 105, 32, 34);
		//LEFT
		enemy_image[6] = ss.grabImage(5, 11, 32, 34);
		enemy_image[7] = ss.grabImage(36, 7, 32, 34);
		enemy_image[8] = ss.grabImage(68, 3, 32, 34);
		//BACKWARD
		enemy_image[9] = ss.grabImage(8, 38, 32, 34);
		enemy_image[10] = ss.grabImage(35, 39, 32, 34);
		enemy_image[11] = ss.grabImage(69, 37, 32, 34);*/
		 
		
		/*
		//FORWARD
		enemy_image[0] = enemy_sheet.grabImage(97, 25, 27, 22);
		enemy_image[1] = enemy_sheet.grabImage(129, 23, 27, 22);
		enemy_image[2] = enemy_sheet.grabImage(159, 21, 31, 22);
		//RİGHT
		enemy_image[3] = enemy_sheet.grabImage(98, 53, 27, 22);
		enemy_image[4] = enemy_sheet.grabImage(130, 55, 27, 22);
		enemy_image[5] = enemy_sheet.grabImage(163, 58, 27, 22);
		//LEFT
		enemy_image[6] = enemy_sheet.grabImage(95, 117, 27, 22);
		enemy_image[7] = enemy_sheet.grabImage(127, 119, 27, 22);
		enemy_image[8] = enemy_sheet.grabImage(158, 122, 27, 22);
		//BACKWARD
		enemy_image[9] = enemy_sheet.grabImage(97, 89, 27, 22);
		enemy_image[10] = enemy_sheet.grabImage(129, 86, 27, 22);
		enemy_image[11] = enemy_sheet.grabImage(159, 84, 31, 22);
		 */
		
		/*
		///THE CORDİNATS THAT I DO
		enemy_image[0] = enemy_sheet.grabImage(35, 5, 27, 22);
		enemy_image[1] = enemy_sheet.grabImage(66, 6, 27, 22);
		enemy_image[2] = enemy_sheet.grabImage(97, 1, 27, 22);
		//RİGHT
		enemy_image[3] = enemy_sheet.grabImage(41, 33, 27, 22);
		enemy_image[4] = enemy_sheet.grabImage(73, 38, 27, 22);
		enemy_image[5] = enemy_sheet.grabImage(107, 98, 27, 22);
		//LEFT
		enemy_image[6] = enemy_sheet.grabImage(38, 97, 27, 22);
		enemy_image[7] = enemy_sheet.grabImage(70, 102, 27, 22);
		enemy_image[8] = enemy_sheet.grabImage(102, 102, 27, 22);
		 
		//BACKWARD
		enemy_image[9] = enemy_sheet.grabImage(35, 69, 27, 22);
		enemy_image[10] = enemy_sheet.grabImage(66, 70, 27, 22);
		enemy_image[11] = enemy_sheet.grabImage(97, 65, 27, 22);
		*/

		
		/*animForward = new Animation(8, enemy_image[0], enemy_image[1], enemy_image[2]);
		animBackward = new Animation(8, enemy_image[3], enemy_image[4], enemy_image[5]);
		animLeft = new Animation(8, enemy_image[6], enemy_image[7], enemy_image[8]);
		animRight= new Animation(8, enemy_image[9], enemy_image[10], enemy_image[11]);*/
	
	} 

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		

		for(int i=0;i<handler.object.size();i++){
			
			GameObject temObject = handler.object.get(i);
			if(temObject.getId() == ID.Player){
				diffX = x - temObject.getX() - width;
				diffY = y - temObject.getY() - height;
				distance = (float) Math.sqrt((x-temObject.getX())* (x-temObject.getX()) + (y - temObject.getY()) * (y - temObject.getY()));
			}
		}	
		
		if(distance < 200){
			velX = ((-1/distance) * diffX);
			velY = ((-1/distance) * diffY);
		}

		else{
			velX = 0;
			velY = 0;
		}
		
		collosion();
		
		
		
		/*animForward.runAnimation();
		animBackward.runAnimation();
		animLeft.runAnimation();
		animRight.runAnimation();*/
	

	}
	
	public void collosion(){
		
		for(int i =0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);
			
			if(temObject.getId() == ID.Block){
				
				if(getBoundsTop().intersects(temObject.getBounds())){
					
					y = temObject.getY() + temObject.getHeight();
					velY = 0;
				}
				
				if(getBoundsBottom().intersects(temObject.getBounds())){
					y = temObject.getY() - height;
					velY=0;

				}
				
				if(getBoundsLeft().intersects(temObject.getBounds())){
					x = temObject.getX() + temObject.getWidth();
					
				}
				if(getBoundsRight().intersects(temObject.getBounds())){
					x = temObject.getX() - temObject.getWidth();
				}
			}
			


		}
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.CYAN);
		g.fillRect((int) x, (int) y, width, height);

		//g.drawImage(enemy_image[0],(int) x,(int) y, null);
		
		/*
		for(int i = 0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);
			if(temObject.getId() == ID.Player){
				if(velX==0 & velY ==0){
					animForward.drawAnimation(g, x, y, 0);
				}else if(Math.abs(temObject.getX()-x) > Math.abs(temObject.getY() - y)){
					if(velX > 0){
						animRight.drawAnimation(g, x, y, 0);
					}else if(velX < 0){
						animLeft.drawAnimation(g, x, y, 0);
					}
				}else if(Math.abs(temObject.getY()-y) > Math.abs(temObject.getX() - x)){
					if(velY > 0){
						animForward.drawAnimation(g, x, y, 0);
					}
					else if(velY < 0){
						animBackward.drawAnimation(g, x, y, 0);
					}
				}
			}
		}*/
		
		/*if(velX > 0){
			animRight.drawAnimation(g, x, y, 0);
		}else if(velX < 0){
			animLeft.drawAnimation(g, x, y, 0);
		}
		if(velX > 0){
			animForward.drawAnimation(g, x, y, 0);
		}
		if(velX > 0){
			animBackward.drawAnimation(g, x, y, 0);
		}
		else if(velX == 0 && velY == 0){
			g.drawImage(enemy_image[0], (int) x, (int) y, null);
		}*/
		
		
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
