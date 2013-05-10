package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends MovableEntity {

	public Image sprite;
	public boolean inAir;

	public Player(String sprite, float posX, float posY) throws SlickException {
		this.sprite = new Image(sprite);

		inAir = true;

		setX(posX);
		setY(posY);

		velocityX = 0f;
		velocityY = 0f;

		maxSpeedX = 8f;
		maxSpeedY = 12f;

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
			if (!inAir) {
				velocityY = -8f;
				inAir = true;
			}
		} else if (input.isKeyDown(Input.KEY_SPACE)) {
			// TODO Shoot lemons.
		}

		if (inAir) {
			if (velocityY < maxSpeedY) {
				velocityY += gravity;
			} else {
				velocityY = maxSpeedY;
			}
		} else {
			velocityY = 0f;
		}

		setX(getX() + velocityX);
		setY(getY() + velocityY);

		setCollisionShapeX(getX());
		setCollisionShapeY(getY());
	}
	
	public void collidedWithGround() {
		inAir = false;
	}
}
