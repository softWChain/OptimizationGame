package com.VEYS.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.VEYS.window.Animation;
import com.VEYS.window.BufferedImageLoader;
import com.VEYS.window.Game;
import com.VEYS.window.SpriteSheet;

public class Menu extends MouseAdapter{
	
	private BufferedImage img,img2,img3,img4,img5;
	private BufferedImageLoader load;
	private Handler handler;

	
	public Menu(){
		load = new BufferedImageLoader();
		
		img = load.loadImage("/playGameButton.png");
		img2 = load.loadImage("/instructionsButton.png");
		img3 = load.loadImage("/exit_button.png");	
		img4 = load.loadImage("/instructions.png");
		img5 = load.loadImage("/go_button.png");
	}
	
	public void tick(){
		

	}
	
	public void render(Graphics g){
	
		if(Game.gameState == STATE.Menu){
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0,Game.WIDTH, Game.HEIGHT);
			g.drawImage(img, 283, 120,null);
			g.drawImage(img2, 283, 190, null);
			g.drawImage(img3, 283, 260, 235 , 70 , null);
			
			
			//createButton(300,150,200,80,"PLAY",360,200,g);
			//createButton(300,250,200,80,"EXİT",365,300,g);
			

		}
		else if(Game.gameState == STATE.instruction){

			g.drawImage(img4, 0, 0, 800 , 600 , null);
			
		}
		else if(Game.gameState == STATE.Game && KeyInput.valueESC == true){

			g.setColor(new Color(242, 226, 226));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.drawImage(img5, 300, 150, 200,200, null);
		}

		
	}
	
	public void createButton(int x,int y,int width,int height,String text,int textX,int textY,Graphics g) {
		Font font = new Font("Arial",Font.ITALIC,30);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);
		g.setFont(font);
		g.drawString(text,textX, textY);
		
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		if(Game.gameState == STATE.Menu){
			if(MouseOver(mx,my,283,120,235,70)){
				Game.gameState = STATE.Game;
			}
			
			if(MouseOver(mx,my,283,190,235,70)){
				Game.gameState = STATE.instruction;
			}
			
			if(MouseOver(mx, my, 283, 270, 235, 70 ) ){
				System.err.println(" System.EXit.0.0.0.1");
				System.exit(0);
				
			}
		}
		else if(Game.gameState == STATE.instruction){
			if(MouseOver(mx, my, 703, 456, 937, 525));
			Game.gameState = STATE.Menu;
		}
		else if(KeyInput.valueESC == true && Game.gameState == STATE.Game){
			if(MouseOver(mx, my, 300, 150, 500, 350 ) ){
				KeyInput.valueESC = false;
				Game.gameState = STATE.Game;			
			}
		}
	}
	
	public static boolean MouseOver(int mx,int my,int x,int y, int width,int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

}
