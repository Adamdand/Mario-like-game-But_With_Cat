package com.biggfoot.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.biggfoot.framework.GameObject;
import com.biggfoot.framework.MouseInput;
import com.biggfoot.framework.ObjectId;
import com.biggfoot.objects.Block;
import com.biggfoot.objects.EvilHands;
import com.biggfoot.objects.Flag;
import com.biggfoot.objects.Player;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private Camera cam;
	
	private BufferedImage level1 = null;
	private BufferedImage level2 = null;
	
	public Handler(Camera cam) {
		this.cam=cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/level.png"); //loading the level2
		level2 = loader.loadImage("/level2.png"); //loading the level2
	}
	
	public void tick() {
		for(int i = 0; i<object.size(); i++) {
			tempObject = object.get(i);			//setting temp object to whatever our object is at this current point in our list
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<object.size(); i++) {
			tempObject = object.get(i);			
			
			tempObject.render(g);
		}
	}
	
	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		 
		System.out.println("width: " + w + " | height: " + h);
		
		for(int xx = 0; xx<h; xx++) {
			for(int yy = 0; yy<w; yy++) {	//looping through every single pixel in our image
				int pixel = image.getRGB(xx,yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));// 255max in colour spectrum = white (0 = dirt block)
				if(red == 128 && green == 128 && blue == 128) addObject(new Block(xx*32, yy*32, 1, ObjectId.Block)); // set blocks this colour to being dirt and grass

				if(red == 0 && green == 0 && blue == 255) addObject(new Player(xx*32, yy*32, this, cam, ObjectId.Player)); //blue pixel = player
				
				if(red == 255 && green == 216 && blue == 0) addObject(new Flag(xx*32, yy*32, ObjectId.Flag)); //Winning Flag
				
				if(red == 255 && green == 0 && blue == 0) addObject(new EvilHands(xx*32, yy*32, this, ObjectId.EvilHands)); //Winning Flag
				
				
			}
		}
	}
	
	public void restartLevel() {
		clearLevel();
		cam.setX(0);
		switch(Game.LEVEL)
		{
			case 1:
				LoadImageLevel(level1);
				break;
			case 2:
				LoadImageLevel(level2);
				break;
		}
	}
	
	public void switchLevel() {
		clearLevel();
		cam.setX(0);
		
		switch(Game.LEVEL)
		{
			case 1:
				LoadImageLevel(level2);
				break;
//			case 2:
//				LoadImageLevel(level3);
//				break;
		}
		Game.LEVEL++;	//increase level after beating each
	}
	
	private void clearLevel() {
		object.clear(); //removes all objects in game when lvl complete
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	

	

//	**************************************THIS WAS FOR MANUAL WHITE BLOCK TILES
//	public void createLevel() {
//		for(int yy = 0; yy < Game.HEIGHT+32; yy += 32) 
//			addObject(new Block(0, yy, ObjectId.Block)); 	//left wall
//		
////		for(int xx = 0; xx < Game.WIDTH+32; xx += 32) 
////			addObject(new Block(xx, 0 , ObjectId.Block)); //xx, Game.HEIGHT-32, ObjectId.Block));	//Top wall
//		
//		for(int xx = 0; xx < Game.WIDTH*2; xx += 32) //*2 so we have more space to move
//			addObject(new Block(xx, Game.HEIGHT-32 , ObjectId.Block));	//bottom wall
//
//		
//		for(int xx = 200; xx < 600; xx += 32) 
//			addObject(new Block(xx, 400 , ObjectId.Block));	 //floating wall
//		
//		
//	
//		
//	}

}
