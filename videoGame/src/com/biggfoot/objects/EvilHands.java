package com.biggfoot.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.biggfoot.framework.GameObject;
import com.biggfoot.framework.ObjectId;
import com.biggfoot.framework.Texture;
import com.biggfoot.window.Animation;
import com.biggfoot.window.Game;
import com.biggfoot.window.Handler;

public class EvilHands extends GameObject {
	
	private float width = 50, height = 50; //48X 96 originaly
	
	Texture tex = Game.getInstance();

	private Animation zombieHand;
	
	private Handler handler;

	public EvilHands(float x, float y, Handler handler,  ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		zombieHand = new Animation(12, tex.hand_grab[0], tex.hand_grab[1], tex.hand_grab[2], tex.hand_grab[3], tex.hand_grab[4]); // higher speed int= slower animation
		
	}

	public void tick(LinkedList<GameObject> object) {
		zombieHand.runAnimation();
		
		Collision(object);	//call method
		
	}
	


	public void render(Graphics g) {
		//g.drawImage(tex.hand_grab[0], (int)x, (int)y, null);
		zombieHand.drawAnimation(g,  (int)x,  (int)y, 50, 50);
		
	}

	public Rectangle getBounds1() {
		
		return new Rectangle ((int)x,(int)y, 32, 32);
		
	}

	private void Collision(LinkedList<GameObject> object) {//check for collision
		for(int i =0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);	//so dont need to retype this every time
			
			if(tempObject.getId() == ObjectId.Bullet) {		//if we collide with a block, execute this code

					
				//RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds())) {	
						y = tempObject.getX() + 35;		
						velY = -5;
						
					}
				
					
				//LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds())) {	
						y = tempObject.getX() + 35;		
						velY = -5;
					}

				}
			}
	}
		public Rectangle getBounds() {	//returns a rectangle around our player, used for collision
			
			return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2); //want in 2 segments top and bottom
		}
		
		public Rectangle getBoundsTop() {	
			
			return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
		}
		
		public Rectangle getBoundsRight() {	
			
			return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10); //-10 and +5 so collition isnt exactly at boundary of our object
		}
		
		public Rectangle getBoundsLeft() {	
			
			return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10); //-10 and +5 so collition isnt exactly at boundary of our object
		}
}
