package se.olofkarlsson.java.heavenvshell.Entities.Player;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.olofkarlsson.java.heavenvshell.GameworldEntities;
import se.olofkarlsson.java.heavenvshell.Entities.Arrow;
import se.olofkarlsson.java.heavenvshell.Entities.Core.Entity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.MovableEntity;

public class Player extends MovableEntity {

	private Random random = new Random();
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

		acceleration = 0.25f;
		deceleration = 0.5f;

		setupCollisionShape(getX(), getY(), 32, 32);
		GameworldEntities.movableEntities.add(this);
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
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			try {
				new Arrow(getX(), getY() + 16 * random.nextFloat(), 32f, 0f);
			} catch (SlickException e) {
				System.err.println("Failed to create projectile, exception: " + e);
			}
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
		
		checkCollision();
	}
	
	public void collidedWithGround() {
		inAir = false;
	}
	
	public void checkCollision() {
		Entity otherEntity;
		
		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (this.getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				
			}
		}
	}
}
