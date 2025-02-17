package com.biggfoot.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images; // how many images we have
	private BufferedImage currentImg;	//current image to display
	
	public Animation(int speedInput, BufferedImage... args) {
		this.speed =speedInput;
		images = new BufferedImage[args.length];
		for(int i = 0; i < args.length; i++) {
			images[i] = args[i];
		}
		frames = args.length;
	}

	public void runAnimation() {
		index++;
		if(index > speed) {
			index = 0;
			nextFrame();
			}
		}
	private void nextFrame() {
		for(int i = 0; i<frames; i++) {	//count = what frame we are currently on. if i=0 and count = 0, use image 0;
			if (count == i)
				currentImg = images[i];
		}
		count ++;
		
		if(count > frames)
			count = 0;
	}
	
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImg,  x,  y,  null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(currentImg,  x,  y, scaleX, scaleY,  null);
	}
	
}
