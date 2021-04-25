package com.biggfoot.framework;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import com.biggfoot.objects.Bullet;
import com.biggfoot.window.Handler;

public class MouseInput extends MouseAdapter {

	private Handler handler;
	// GameObject tempPlayer = null; //need to get location of Object so we know
	// where to spawn object

	public MouseInput(Handler handler) { // need handler to create object when pressed
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if (SwingUtilities.isLeftMouseButton(e)) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempPlayer = handler.object.get(i);

				if (tempPlayer.getId() == ObjectId.Player) {
					if (tempPlayer != null) {
						handler.addObject(new Bullet(tempPlayer.getX() + 0, tempPlayer.getY() + 10, ObjectId.Bullet,
								tempPlayer.getFacing() * 10));
						tempPlayer.setAttacking(true);
					}
				}
			}
		}
		
		
		
//		if (SwingUtilities.isRightMouseButton(e)) {
//
//			for (int i = 0; i < handler.object.size(); i++) {
//				GameObject tempPlayer = handler.object.get(i);
//
//				if (tempPlayer.getId() == ObjectId.Player) {
//					if (tempPlayer != null) {
//						handler.addObject(new Bullet(tempPlayer.getX() + 0, tempPlayer.getY() + 10, ObjectId.Bullet,
//								tempPlayer.getFacing() * 10));
//					}
//				}
//			}
//		}
	}
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < handler.object.size(); i++) {
		GameObject tempPlayer = handler.object.get(i);
		tempPlayer.setAttacking(false);
		}
	}

}
