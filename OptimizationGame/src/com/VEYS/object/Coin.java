package com.VEYS.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.VEYS.framework.GameObject;
import com.VEYS.framework.ID;
import com.VEYS.window.Animation;
import com.VEYS.window.SpriteSheet;

public class Coin extends GameObject{
	
	
	private BufferedImage[] coin_Images = new BufferedImage[6];
	
	private Animation anim;


	public Coin(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id);
		falling  = false;
		width = 32;
		height = 32;
		//img = ss.grabImage(84, 0, 14, 20);
		
		coin_Images[0] = ss.grabImage(64, 0, 18, 20);
		coin_Images[1] = ss.grabImage(83, 0, 18, 20);
		coin_Images[2] = ss.grabImage(101, 0, 18, 20);
		coin_Images[3] = ss.grabImage(120, 0, 18, 20);
		coin_Images[4] = ss.grabImage(139, 0, 18, 20);
		coin_Images[5] = ss.grabImage(155, 0, 18, 20);
		
		anim = new Animation(20, coin_Images[0], coin_Images[1], coin_Images[2], coin_Images[3], coin_Images[4], coin_Images[5]);
	}
	


	@Override
	public void tick() {
		anim.runAnimation();
		
	}

	@Override
	public void render(Graphics g) {

		anim.drawAnimation(g, (int) x, (int) y, 0);
		
	}

	@Override
	public Rectangle getBounds() {
		return  new Rectangle((int) x, (int) y, width , height);
	}

}
