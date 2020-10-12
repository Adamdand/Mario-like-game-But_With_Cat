package com.biggfoot.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.biggfoot.framework.GameObject;
import com.biggfoot.framework.ObjectId;
import com.biggfoot.framework.Texture;
import com.biggfoot.window.Animation;
import com.biggfoot.window.Camera;
import com.biggfoot.window.Game;
import com.biggfoot.window.Handler;

public class Bullet extends GameObject {
	
	Texture tex = Game.getInstance();
	private Animation hairBallRight;	
	private Animation hairBallLeft;

	public Bullet(float x, float y, ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
		
		hairBallRight = new Animation(2, tex.hairball[0], tex.hairball[1], tex.hairball[2], tex.hairball[3], tex.hairball[4] );
		hairBallLeft = new Animation(2, tex.hairball[5], tex.hairball[6], tex.hairball[7], tex.hairball[8], tex.hairball[9] );

	}


	public void tick(LinkedList<GameObject> object) {
		x += velX;
		
		if(velX < 0) facing = -1;
		else if (velX > 0 ) facing = 1;
		
		hairBallLeft.runAnimation();
		hairBallRight.runAnimation();

		
	}


	public void render(Graphics g) {
		//g.setColor(Color.green);
		//g.fillRect((int)x, (int)y, 16,16);
		//WALKING
		
//			if(facing ==1)
//				g.drawImage(tex.hairball[2], (int)x, (int)y, null);	//WALKING right
//			else if (facing == -1)
//				g.drawImage(tex.hairball[7], (int)x, (int)y, null);	//walking left
		
		//g.drawImage(tex.hairball[0], (int)x, (int)y, null); //when cat used to shoot static objects
			
			if(facing ==1)
				hairBallRight.drawAnimation(g,  (int)x,  (int)y, 50, 50);	//animation
			else
				hairBallLeft.drawAnimation(g,  (int)x,  (int)y, 50, 50);	//animation

		
	}


	public Rectangle getBounds() {

		return new Rectangle((int)x,(int)y, 16,16);
	}
	
}
