package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends StatEntity {

	public Image sprite;
	public boolean jumping;
	public float velocityY;
	public float movementSpeed;

	public Player(String sprite) throws SlickException {
		this.sprite = new Image(sprite);
		this.jumping = false;
		this.velocityY = 0f;
		this.movementSpeed = 4f;
		this.setupCollisionShape(32, 32);
	}

	public void draw() {
		sprite.draw(this.getX(), this.getY());
	}
}
