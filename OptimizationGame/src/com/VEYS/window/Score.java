package com.VEYS.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
	
	private int point;
	private float healt = 100;

	
	public void tick(){	
	}
	
	public void render(Graphics g){
		
		//HEALT REVİEW
		g.setColor(Color.RED);
		g.fillRect(15, 15, 200, 25);
		
		//HEALT
		Font font1 = new Font("Arial",Font.ITALIC,12);
		g.setColor(Color.CYAN);
		g.setFont(font1);
		g.drawString("HEALTH", 15, 13);
		g.fillRect(15, 15, (int) healt*2, 25);


		//SCORE
		Font font = new Font("Arial",Font.BOLD,25);
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("SCORE : " + point ,650, 40);
	}
	
	public int getPoint(){
		return point;
	}
	public void setPoint(int point){
		this.point = point;
	}
	
	public float getHealt(){
		return healt;
	}
	
	public void setHealt(float healt){
		this.healt = healt;
	}

}
