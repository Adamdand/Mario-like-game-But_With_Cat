package com.biggfoot.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected float x,y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean attacking = false;
	protected int facing = 1;
	
	public GameObject(float x, float y, ObjectId id) {
		
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object); //for collision detection
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds(); //got colision bounding for our player
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public  void setX(float x) {
		this.x = x;
	}
	public  void setY(float y) {
		this.y = y;
	}
	
	public  float getVelX() {
		return velX;
	}
	public  float getVelY() {
		return velY;
	}
	public  void setVelX(float Xvel) {
		this.velX = Xvel;
	}
	public  void setVelY(float Yvel) {
		this.velY = Yvel;
	}
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public int getFacing() {
		return facing;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	public boolean isAttacking() {
		return attacking;
	}

	public ObjectId getId() {
		return id;
	}
	
	}


