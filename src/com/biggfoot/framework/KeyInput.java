package com.biggfoot.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.biggfoot.objects.Bullet;
import com.biggfoot.window.Handler;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();//each key has a code, storing code in the key
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {			//check, see if we are running through objects/players/blocks
				if(key == KeyEvent.VK_D) 
					tempObject.setVelX(5);	//if player (D/W/S/A for keyboard controls)
				
				if(key == KeyEvent.VK_A) 
					tempObject.setVelX(-5); 
				
				if(key == KeyEvent.VK_RIGHT) 
					tempObject.setVelX(5); 
				
				if(key == KeyEvent.VK_LEFT) 
					tempObject.setVelX(-5); 
				
				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()) { 
					tempObject.setJumping(true);
					tempObject.setVelY(-10); 							//Jumping Height *************************
					//handler.addObject(new Bullet(tempObject.getX()+0, tempObject.getY() + 10, ObjectId.Bullet, tempObject.getFacing() * 10)); //set velocity to 5, position (+10,+25) //SPLACEBAR SHOOTING
											
				}
					
				if(key == KeyEvent.VK_W && !tempObject.isJumping()) { // W_KEY jumping
					tempObject.setJumping(true);
					tempObject.setVelY(-10); 							//Jumping Height *************************
				}
					
				if(key == KeyEvent.VK_UP && !tempObject.isJumping()) { // UP_KEY jumping
					tempObject.setJumping(true);
					tempObject.setVelY(-10); 							//Jumping Height *************************
					
					
				}
				//Extra-combos
				if(key == KeyEvent.VK_SPACE && key == KeyEvent.VK_RIGHT &&!tempObject.isJumping()) { 
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
					tempObject.setVelX(5);
				}
			
				if(key == KeyEvent.VK_SPACE && key == KeyEvent.VK_LEFT &&!tempObject.isJumping()) { 
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
					tempObject.setVelX(-5);
				}
			}
		}
		
		
		
		
		
		if (key == KeyEvent.VK_ESCAPE) {	//instead of ASKY CODE of escape, can use this, enum for actual asky code number
			System.exit(1);
		}
		
	}
	
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();//each key has a code, storing code in the key
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {			//check, see if we are running through objects/players/blocks
				if(key == KeyEvent.VK_D)tempObject.setVelX(0);	//if player (D/W/S/A for keyboard controls)
				if(key == KeyEvent.VK_A)tempObject.setVelX(0); 	//0 for no more movement when key is released
				if(key == KeyEvent.VK_RIGHT)tempObject.setVelX(0);	//if player (D/W/S/A for keyboard controls)
				if(key == KeyEvent.VK_LEFT)tempObject.setVelX(0);	//if player (D/W/S/A for keyboard controls)
			}
		}
	}
	

}
