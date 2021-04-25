package com.biggfoot.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.biggfoot.framework.GameObject;
import com.biggfoot.framework.KeyInput;
import com.biggfoot.framework.MouseInput;
import com.biggfoot.framework.ObjectId;
import com.biggfoot.framework.Texture;
import com.biggfoot.objects.Block;
import com.biggfoot.objects.Flag;
import com.biggfoot.objects.Player;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8338311474994350626L;
	
	private boolean running = false;
	
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	public BufferedImage level= null, graveyard = null, tree = null, cloud = null;
	//private BufferedImage spriteSheet= null;
	
	//Object
	Handler handler;
	MouseInput mInput;
	Camera cam;
	static Texture tex;
	
	Random rand = new Random();
	
	public static int LEVEL = 1;
	
	private void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); //loading the level
		
		graveyard = loader.loadImage("/background_graveyard.png");	//load background general
		tree = loader.loadImage("/Tree.png");	//load background trees
		cloud = loader.loadImage("/cloud1.png");	//load background trees
		
		cam = new Camera(0,0);	//initialize camera when game starts/new level
		handler = new Handler(cam);
		
		cam = new Camera(0, 0); //starting coordinates of our camera
		
		handler.LoadImageLevel(level);
		
		//handler.addObject(new Player(100, 100, handler, ObjectId.Player));
		
		//handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
		mInput = new MouseInput(handler);
		this.addMouseListener(mInput);
	
		
	}
	
	public synchronized void start() {
		
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	

	public void run() {
		
		init();
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;			//game speed
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS:" + frames + " Ticks: " + updates);
				//fps = frames;
				//ticks = updates;
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick() {
		
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));	//only one player, so game ticks with proper object that we want
			}
		}
		
		
	}
	
	private void render() { //graphical stuff
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);		//pre-loading next image behind the current if there is time/comp speed - depends on computer speed,  3 is about max
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g; //graphics2d has a translation function
		///////////////////////////////
		
		
		//draw here
		g.setColor(new Color(25,191,224));	//set color of background
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		g.drawImage(graveyard, 0, 0, 1200, 700, this);	//place and size back ground
		
		g2d.translate(cam.getX(), cam.getY());	//begin of cam
		for(int xx = 0; xx<cloud.getWidth() * 10; xx += cloud.getWidth()*2) //insert trees
			g.drawImage(cloud, xx, 0, this);	//place and size trees
		
			handler.render(g);						//objects in here will be seen by camera
			for(int xx = 0; xx<tree.getWidth() * 10; xx += tree.getWidth()*2) //insert trees
				g.drawImage(tree, xx, 370, this);	//place and size trees
		
		g2d.translate(cam.getX(), -cam.getY());	//end of cam
		
		
		
		///////////////////////////////
		g.dispose();
		bs.show();
	}
	

	
	public static Texture getInstance() {
		return tex;
	}
	
	
	public static void main(String args[]) {
		new Window(800,600, "Schrodinger's Cat", new Game()); //GAME NAME
		
	}

}
