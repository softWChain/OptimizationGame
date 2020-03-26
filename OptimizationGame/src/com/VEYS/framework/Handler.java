package com.VEYS.framework;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	public ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	private boolean right,left = false;
	
	public void tick(){
		
		for(int i=0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g){
		
		for(int i=0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	

	

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	
	

}
