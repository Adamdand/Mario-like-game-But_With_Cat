package com.biggfoot.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.biggfoot.framework.GameObject;
import com.biggfoot.framework.ObjectId;
import com.biggfoot.framework.Texture;
import com.biggfoot.window.Game;

public class Flag extends GameObject{
	
	Texture tex = Game.getInstance();

	public Flag(float x, float y, ObjectId id) {
		super(x, y, id);

	}


	public void tick(LinkedList<GameObject> object) {

	}


	public void render(Graphics g) {
		//g.setColor(Color.yellow);
		//g.fillRect((int)x, (int)y, 32,32);
		g.drawImage(tex.flag[0], (int)x, (int)y, null);

		
	}


	public Rectangle getBounds() {

		return new Rectangle((int)x,(int)y, 32,32);
	}
	
}
