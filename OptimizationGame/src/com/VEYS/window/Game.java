package com.VEYS.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.VEYS.framework.Camera;
import com.VEYS.framework.Handler;
import com.VEYS.framework.ID;
import com.VEYS.framework.KeyInput;
import com.VEYS.framework.Menu;
import com.VEYS.framework.STATE;
import com.VEYS.object.Block;
import com.VEYS.object.Bullet;
import com.VEYS.object.Coin;
import com.VEYS.object.Enemy;
import com.VEYS.object.Player;

public class Game extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = -3963226356702865080L;

	
	public static int WIDTH = 800, HEIGHT = 600;
	private String TITLE = " Game. pre-Alpha 0.0.1";
	private boolean running = false;
	
	private Thread thread;
	private Handler handler;

	private BufferedImage level;
	private BufferedImageLoader loader;
	private BufferedImage player_Image;
	private SpriteSheet player_Sheet;
	private BufferedImage block_Image;
	private SpriteSheet block_Sheet;
	private BufferedImage coin_Image;
	private SpriteSheet coin_Sheet;
	public static Image gifAnimation;


	private BufferedImage enemy_image1;
	private SpriteSheet enemy_sheet;
	public static BufferedImage bullet_image;
	public static SpriteSheet bullet_sheet;
	
	private Camera camera;
	public static STATE gameState = STATE.Menu;
	private Menu menu;
	private Window window;
	private Coin coin;
	private Score score;
	private Enemy enemy;
	private Bullet bullet;
	private Explosion explosion;
	


	public Game(){
		
		window = new Window(WIDTH, HEIGHT, TITLE, this);
		
	}
	
	public void init(){
		
		handler = new Handler();
		score = new Score();
		loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
		
		block_Image = loader.loadImage("/sprite_sheets.png");
		block_Sheet = new SpriteSheet(block_Image);
		
		player_Image = loader.loadImage("/player_sprite_sheet.png");
		player_Sheet = new SpriteSheet(player_Image);
		
		coin_Image = loader.loadImage("/sprite_sheet.png");
		coin_Sheet = new SpriteSheet(coin_Image);
		
		enemy_image1 = loader.loadImage("/bird_robin.png");
		enemy_sheet = new SpriteSheet(enemy_image1);
		
		bullet_image = loader.loadImage("/bullet.png");
		bullet_sheet = new SpriteSheet(bullet_image);
		
		gifAnimation = Toolkit.getDefaultToolkit().createImage("res/giphy.gif");

		
	
		
		addKeyListener(new KeyInput(handler));
		addMouseListener(new Menu());
		loadLevel(level);
		camera = new Camera(0, 0);
		menu = new Menu();
		explosion = new Explosion();
		
		
		
	}
	
	public void tick(){
		
		if(gameState == STATE.Game && KeyInput.valueESC == false){
			
			handler.tick();
			score.tick();
			
			for(int i=0;i<handler.object.size();i++){
				if(handler.object.get(i).getId() == ID.Player){
					camera.tick(handler.object.get(i));
					explosion.tick();
				}
			}
			
		}else if(gameState == STATE.Menu || gameState ==STATE.instruction){
			menu.tick();
		}
		
		
	}
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Game && KeyInput.valueESC == false ){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			score.render(g);
			g2d.translate(camera.getX(), camera.getY());
			handler.render(g);
			g2d.translate(-camera.getX(), -camera.getY());
		
		}else if(gameState == STATE.Menu || gameState ==STATE.instruction || KeyInput.valueESC == true){
			menu.render(g);
			
		}

		
		bs.show();
		g.dispose();
		
		
	}

	public void loadLevel(BufferedImage img){
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		for(int yy=0;yy<height;yy++){
			for(int xx=0;xx<width;xx++){
				int pixel = img.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int blue = (pixel >> 8) & 0xff;
				int green = (pixel) & 0xff;
				
				if(red == 0 && blue == 38 && green == 255){
					handler.addObject(new Player(xx*32, yy*32, ID.Player, handler,score, player_Sheet));
				}
				if(red == 255 && blue == 255 && green == 255){
					handler.addObject(new Block(xx*32, yy*32, ID.Block, block_Sheet,handler));
				}
				if(red == 0 && blue == 255 && green == 255){
				
					handler.addObject(new Coin(xx*32, yy*32, ID.Coin, coin_Sheet));
				}
				if(red == 255 && green == 0 && blue == 0){
					handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler,enemy_sheet));
				}


			}
				
		}
		
		
	}
	
	@Override
	public void run() {
		
		init();
		requestFocus();
	
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double delta = 0;
		double timePerSecond = 1000000000 / amountOfTicks;
		long now;
		long timer = System.currentTimeMillis() ;
		int  frames = 0;
		int updates = 0;
		
		
		while(running){
			try {
				thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			now = System.nanoTime();
			delta += (now - lastTime) / timePerSecond;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				
				timer += 1000;
				window.getFrame().setTitle(((TITLE + "   FPS : " + frames +" , UPDATES : " + updates)));
				System.err.println((TITLE + "   FPS : " + frames +" , UPDATES : " + updates));
				frames  = 0;
				updates = 0;

				
			}
		}
		
		stop();
		
		
	}
	
	public synchronized void start(){
		
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args){
		
		new Game();
	}


	

}
