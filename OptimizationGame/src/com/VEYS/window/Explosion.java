package com.VEYS.window;

import com.VEYS.object.Enemy;

public class Explosion{
	private int temp=0;
	
	
	public void tick() {
		
		if(Enemy.gifAnim == true){
			if(temp<17){
				temp++;
			}
			else if(temp==17){
				Game.gifAnimation.flush();
				Enemy.gifAnim = false;	
				temp=0;
			}
			
		}
		
	}

}
