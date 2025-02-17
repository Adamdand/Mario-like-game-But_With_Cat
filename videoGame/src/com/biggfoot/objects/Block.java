package com.biggfoot.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.biggfoot.framework.GameObject;
import com.biggfoot.framework.ObjectId;
import com.biggfoot.framework.Texture;
import com.biggfoot.window.Game;

public class Block extends GameObject {
	
	Texture tex = Game.getInstance();
	private int type;
	
	public Block(float x, float y, int type,  ObjectId id) {
	super(x, y, id);
	this.type=type;
}


	public void tick(LinkedList<GameObject> object) {
		
	}
	
	public void render(Graphics g) {
		
//		g.setColor(Color.white);		//this was for the original white blocks for tiles
//		g.drawRect((int)x, (int)y, 32, 32);
		
		if(type ==0) //dirt block
			g.drawImage(tex.block[0], (int)x, (int)y, null);
		if(type ==1) //grass block
			g.drawImage(tex.block[1], (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32 ,32);
	}





}
