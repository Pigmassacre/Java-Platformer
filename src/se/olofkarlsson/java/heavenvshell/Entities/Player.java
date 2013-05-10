package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends MovableEntity {

	public Image sprite;
	public boolean jumping;

	public Player(String sprite, float posX, float posY) throws SlickException {
		this.sprite = new Image(sprite);

		jumping = false;

		setX(posX);
		setY(posY);

		velocityX = 0f;
		velocityY = 0f;

		maxSpeedX = 8f;
		maxSpeedY = 16f;

		acceleration = 0.1f;
		deceleration = 0.25f;

		setupCollisionShape(getX(), getY(), 32, 32);
	}

	public void draw() {
		sprite.draw(getX(), getY());
	}

	public void update(Input input, float gravity) {
		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (velocityX > -maxSpeedX) {
				velocityX -= acceleration;
			} else {
				velocityX = -maxSpeedX;
			}
		} else if (velocityX < 0) {
				velocityX += deceleration;
			if (velocityX > 0) {
				velocityX = 0;
			}
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (velocityX < maxSpeedX) {
				velocityX += acceleration;
			} else {
				velocityX = maxSpeedX;
			}
		} else if (velocityX > 0) {
			velocityX -= deceleration;
			if (velocityX < 0) {
				velocityX = 0;
			}
		}

		if (input.isKeyDown(Input.KEY_RALT)) {
			if (!jumping) {
				velocityY = 6f;
				//jumping = true;
			}
		} else if (input.isKeyDown(Input.KEY_SPACE)) {

		}

		setX(getX() + velocityX);
		setY(getY() + velocityY + gravity);

		setCollisionShapeX(getX());
		setCollisionShapeY(getY());
	}
}
