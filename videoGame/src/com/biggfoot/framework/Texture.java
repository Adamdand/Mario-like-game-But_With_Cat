package com.biggfoot.framework;

import java.awt.image.BufferedImage;

import com.biggfoot.window.BufferedImageLoader;

public class Texture {
	
	SpriteSheet bs; //block sheet
	SpriteSheet ps; //player sheet
	SpriteSheet hand; //player sheet
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	
	public BufferedImage[] block = new BufferedImage[2]; //grass and dirt for now
	public BufferedImage[] player = new BufferedImage[16]; //for player walking images
	public BufferedImage[] player_jump = new BufferedImage[10]; //for player jumping
	public BufferedImage[] flag = new BufferedImage[1]; //for player jumping
	public BufferedImage[] hairball = new BufferedImage[10]; //for player projectile
	public BufferedImage[] hand_grab = new BufferedImage[5]; //for player projectile
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);

		
		getTextures();
		
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(4, 3, 32, 32); //dirt block (col,row, size,size)
		block[1] = bs.grabImage(3, 1, 32, 33); //dirt with grass on top block
		
		flag[0] = bs.grabImage(12, 5, 31, 29); //dirt with grass on top block
		
		
		//HAND
		hand_grab[0] = bs.grabImage(1, 5, 45 , 53);
		hand_grab[1] = bs.grabImage(2, 5, 45 , 53);
		hand_grab[2] = bs.grabImage(3, 5, 45 , 53);
		hand_grab[3] = bs.grabImage(4, 5, 45 , 53);
		hand_grab[4] = bs.grabImage(5, 5, 45 , 53);
		
		//LOOKING RIGHT
		player[0] = ps.grabImage(1,1,32,32); //idle frame - player (1,1,440,460)
		player[1] = ps.grabImage(2,1,32,32); //walking animation - player
		player[2] = ps.grabImage(3,1,32,32); //walking animation - player
		player[3] = ps.grabImage(4,1,32,32); //walking animation - player
		player[4] = ps.grabImage(5,1,32,32); //walking animation - player
		player[5] = ps.grabImage(6,1,32,32); //walking animation - player
		player[6] = ps.grabImage(7,1,32,32); //walking animation - player
		player[7] = ps.grabImage(8,1,32,32); //walking animation - player
		
		//LOOKING LEFT
		player[8] = ps.grabImage(1,2,32,32); //idle frame - player (1,1,440,460)
		player[9] = ps.grabImage(2,2,32,32); //walking animation - player
		player[10] = ps.grabImage(3,2,32,32); //walking animation - player
		player[11] = ps.grabImage(4,2,32,32); //walking animation - player
		player[12] = ps.grabImage(5,2,32,32); //walking animation - player
		player[13] = ps.grabImage(6,2,32,32); //walking animation - player
		player[14] = ps.grabImage(7,2,32,32); //walking animation - player
		player[15] = ps.grabImage(8,2,32,32); //walking animation - player
		
		//JUMP RIGHT
		player_jump[0] = ps.grabImage(1, 3, 32, 32); //jumping animation - player
		player_jump[1] = ps.grabImage(2, 3, 32, 32); //jumping animation - player
		player_jump[2] = ps.grabImage(3, 3, 32, 32); //jumping animation - player
		player_jump[3] = ps.grabImage(4, 3, 32, 32); //jumping animation - player
		player_jump[4] = ps.grabImage(5, 3, 32, 32); //jumping animation - player ******* used

		//JUMP LEFT
		player_jump[5] = ps.grabImage(1, 4, 32, 32); //jumping animation - player
		player_jump[6] = ps.grabImage(2, 4, 32, 32); //jumping animation - player
		player_jump[7] = ps.grabImage(3, 4, 32, 32); //jumping animation - player
		player_jump[8] = ps.grabImage(4, 4, 32, 32); //jumping animation - player
		player_jump[9] = ps.grabImage(5, 4, 32, 32); //jumping animation - player ***** used
		
		//HairBall Right
		hairball[0] = ps.grabImage(1,5,32,32);
		hairball[1] = ps.grabImage(2,5,32,32);
		hairball[2] = ps.grabImage(3,5,32,32);
		hairball[3] = ps.grabImage(4,5,32,32);
		hairball[4] = ps.grabImage(5,5,32,32);
		
		
		//HairBall Left
		hairball[5] = ps.grabImage(1,6,32,32);
		hairball[6] = ps.grabImage(2,6,32,32);
		hairball[7] = ps.grabImage(3,6,32,32);
		hairball[8] = ps.grabImage(4,6,32,32);
		hairball[9] = ps.grabImage(5,6,32,32);

		

		
		

		
		
	}

}
