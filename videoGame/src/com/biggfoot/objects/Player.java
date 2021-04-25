package com.biggfoot.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.biggfoot.framework.GameObject;
import com.biggfoot.framework.ObjectId;
import com.biggfoot.framework.Texture;
import com.biggfoot.window.Animation;
import com.biggfoot.window.Camera;
import com.biggfoot.window.Game;
import com.biggfoot.window.Handler;

public class Player extends GameObject{
	
	private float width = 50, height = 50; //48X 96 originaly
	private float gravity = 0.5f; //add gravity (how fast do we want it to fall)
	private final float MAX_SPEED = 10; //max fall speed, or else gravity will make character fall really fast eventually, vel Y never greater than 10
	
	//1 = right
	//-1 = left

	private Handler handler;
	private Camera cam;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalk, playerWalkLeft;	//WALKING
	private Animation playerIdleRight, playerIdleLeft;	//IDLE
	private Animation playerHairballRight,playerHairballLeft;
	//private Animation playerJumpRight, playerJumpLeft; //Jumping
	
	
	public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		
		playerWalk = new Animation(5, tex.player_jump[0], tex.player_jump[1], tex.player_jump[2], tex.player_jump[3], tex.player_jump[4]);
		playerWalkLeft = new Animation(5, tex.player_jump[5], tex.player_jump[6], tex.player_jump[7], tex.player_jump[8], tex.player_jump[9]);
		
		playerIdleRight = new Animation(5, tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13], tex.player[14], tex.player[15] );
		playerIdleLeft = new Animation(5, tex.player[0], tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6], tex.player[7]);
		playerHairballRight = new Animation(5, tex.Cathairball[0]);
		playerHairballLeft = new Animation(5, tex.Cathairball[1]);
		//playerJumpRight = new Animation(10, tex.player_jump[0], tex.player_jump[1], tex.player_jump[2], tex.player_jump[3], tex.player_jump[4], tex.player_jump[5], tex.player_jump[6], tex.player_jump[7]);
		//playerJumpLeft = new Animation(10, tex.player_jump[9], tex.player_jump[10], tex.player_jump[11], tex.player_jump[12], tex.player_jump[13], tex.player_jump[14], tex.player_jump[15]);
	}


	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(velX < 0) facing =  -1;
		else if (velX > 0) facing = 1;
		
		
		if(falling || jumping) {	//in these cases, gravity will take effect
			velY += gravity;
			
			if(velY > MAX_SPEED){
				velY = MAX_SPEED;
			}
		}
		
		Collision(object);	//call method
		
		playerWalk.runAnimation(); //WALKING
		playerWalkLeft.runAnimation(); //WALKING
		
		playerIdleRight.runAnimation(); //IDLE
		playerIdleLeft.runAnimation(); //IDLE
		
		playerHairballLeft.runAnimation(); //attacking
		playerHairballRight.runAnimation(); //attacking
		
		//playerJumpRight.runAnimation(); //Jumping
		//playerJumpLeft.runAnimation(); //Jumping

	}
	
	private void Collision(LinkedList<GameObject> object) {//check for collision
		for(int i =0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);	//so dont need to retype this every time
			
			if(tempObject.getId() == ObjectId.Block) {		//if we collide with a block, execute this code
				
				//TOP
					if(getBoundsTop().intersects(tempObject.getBounds())) {	
						y = tempObject.getY() + 32;	//top of collition needs to be ~ half the dimensions of the block
						velY = 0;
						attacking = false;
					}
				
				//BOTTOM
					if(getBounds().intersects(tempObject.getBounds())) {	
					y = tempObject.getY() - height;//snap playing to the top when colliding with bottom (so no overlap)
					velY = 0;
					falling = false;
					jumping = false;
				}else 
					falling = true;

					
				//RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds())) {	
						x = tempObject.getX() - width -2;
					}
				
					
				//LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds())) {	
						x = tempObject.getX() + 35;				
					}
				}else if(tempObject.getId() == ObjectId.Flag) {								//****when flag get touched, next level
					//switch levels
					if(getBounds().intersects(tempObject.getBounds())) {
					attacking = false;
					handler.switchLevel();
					}
				}else if (tempObject.getId() == ObjectId.EvilHands) {
					//reset level
					if(getBounds().intersects(tempObject.getBounds())) {
					attacking = false;
					handler.restartLevel();
					}
				}
			}
	}
		
	



	public void render(Graphics g) {	//render out a blue square with dimension above
		g.setColor(Color.blue);
		
		//Attacking
		if(attacking) {
			if(facing == 1)
				g.drawImage(tex.Cathairball[0],  (int)x,  (int)y,  50,  50,  null); //jump right
			else if(facing == -1)
				g.drawImage(tex.Cathairball[1],  (int)x,  (int)y,  50,  50,  null);	//jump left
			
		}else {
		
		//JUMPING
		if(jumping) {
			if(facing == 1)
				g.drawImage(tex.player_jump[3],  (int)x,  (int)y,  50,  50,  null); //jump right
			else if(facing == -1)
				g.drawImage(tex.player_jump[8],  (int)x,  (int)y,  50,  50,  null);	//jump left
			
		}else {
		
			//WALKING
		if(velX != 0) {
			if(facing ==1)
				playerWalk.drawAnimation(g,  (int)x,  (int)y, 50, 50);	//WALKING right
			else
				playerWalkLeft.drawAnimation(g,  (int)x,  (int)y, 50, 50);	//walking left
			}else {
				if(facing == 1)
					playerIdleRight.drawAnimation(g,  (int)x,  (int)y, 50, 50);	//walking left //*********** CAN SCALE IMAGE HERE	//standing right
				else if( facing == -1)
					playerIdleLeft.drawAnimation(g,  (int)x,  (int)y, 50, 50);	//walking left	//standing left
			}
		}
		}
		
		// g.fillRect((int)x, (int)y, (int)width, (int)height);  // this was for when the player was a rectange
		
		
		Graphics2D g2d = (Graphics2D) g;
		
//		g.setColor(Color.RED); //set colour around box so we can see collision area
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsTop());

		
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
