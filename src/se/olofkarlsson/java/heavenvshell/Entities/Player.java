package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends StatEntity {

	public Image playerSprite;
	public boolean jumping;
	public float velocityY;
	public float movementSpeed;

	public Player(String playerSprite) throws SlickException {
		this.playerSprite = new Image(playerSprite);
		this.jumping = false;
		this.velocityY = 0f;
		this.movementSpeed = 4f;
	}

	public void draw(float x, float y) {
		playerSprite.draw(x, y);
	}
}
