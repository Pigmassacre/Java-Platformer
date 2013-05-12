package se.olofkarlsson.java.heavenvshell.Entities.Player;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.olofkarlsson.java.heavenvshell.GameworldEntities;
import se.olofkarlsson.java.heavenvshell.Entities.Arrow;
import se.olofkarlsson.java.heavenvshell.Entities.Core.CollisionEntity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.MovableEntity;

public class Player extends MovableEntity {

	private Random random = new Random();
	public Image sprite;
	public Graphics debugGraphics;
	public boolean inAir;

	public Player(String sprite, float posX, float posY) throws SlickException {
		this.sprite = new Image(sprite);

		debugGraphics = new Graphics();

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
		GameworldEntities.entitiesMovable.add(this);
	}

	public void draw() {
		sprite.draw(getX(), getY());
		//debugGraphics.draw(getCollisionShape());
	}

	public void update(Input input, float gravity) {
		CollisionEntity otherEntity = null;
		float newX, newY;

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
				System.err.println("Failed to create projectile, exception: "
						+ e);
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

		newX = getX() + velocityX; // this is where we want to go
		newY = getY() + velocityY;

		// handle collision

		setCollisionShapeX(newX); // first check x-axis

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				System.out.println("Collision on x-axis!");

				if (velocityX > 0) { // right
					newX = otherEntity.getCollisionShape().getX()
							- (getCollisionShape().getWidth() + 1);
				} else if (velocityX < 0) { // left
					newX = otherEntity.getCollisionShape().getX()
							+ (getCollisionShape().getWidth() + 1);
				} else {
					newX = getX();
				}

				velocityX = 0f;
			}
		}
		
		setX(newX); // update x position now
		setCollisionShapeX(newX); // re-adjust collision box
		
		setCollisionShapeY(newY); // now check y axis

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				System.out.println("Collision on y-axis!");

				inAir = false;

				if (velocityY > 0) { // down
					newY = otherEntity.getCollisionShape().getY()
							- (getCollisionShape().getHeight() + 1);
				} else if (velocityY < 0) { // up
					newY = otherEntity.getCollisionShape().getY()
							+ (getCollisionShape().getHeight() + 1);
				} else {
					newY = getY();
				}
				
				velocityY = 0f;
			} else {
				inAir = true;
			}
		}

		setY(newY); // update y position now
		setCollisionShapeY(newY); // re-adjust collision box again

		System.out.println("Player pos: " + getX() + " " + getY());
	}

}
