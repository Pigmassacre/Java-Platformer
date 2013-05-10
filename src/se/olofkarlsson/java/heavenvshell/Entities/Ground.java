package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ground extends RectangleEntity {
	// TODO Make a ground entity that the player can collide and stand atop on.

	public Image sprite;
	
	public Ground(String sprite) throws SlickException {
		this.sprite = new Image(sprite);
		this.setupCollisionShape(32, 32);
	}
	
	public void draw() {
		sprite.draw(this.getX(), this.getY());
	}
}
