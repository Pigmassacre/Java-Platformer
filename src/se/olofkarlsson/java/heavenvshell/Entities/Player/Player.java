package se.olofkarlsson.java.heavenvshell.Entities.Player;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import se.olofkarlsson.java.heavenvshell.GameworldEntities;
import se.olofkarlsson.java.heavenvshell.Entities.Arrow;
import se.olofkarlsson.java.heavenvshell.Entities.Core.CollisionEntity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.Entity;
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
		debugGraphics.draw(getCollisionShape());
	}

	public void update(Input input, float gravity) {
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
		
		newX = getX() + velocityX;
		newY = getY() + velocityY;

		setX(newX);
		setY(newY);

		setCollisionShapeX(getX());
		setCollisionShapeY(getY());

		//System.out.println("Player pos: " + getX() + " " + getY());
		
		checkCollision();
	}

	public void collidedWithGround() {
		inAir = false;
	}
	
	public void checkCollision() {
		
	}
	
	/*
	public void checkCollision() {
		CollisionEntity otherEntity;
		float maxX = 0f, maxY = 0f, minX = 0f, minY = 0f, otherMaxX = 0f, otherMaxY = 0f, otherMinX = 0f, otherMinY = 0f;
		float differenceX = 0f, differenceY = 0f;
		
		//System.out.println("velocityY: " + velocityY + ", velocityX: " + velocityX);
		
		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				System.out.println("Collided with " + otherEntity.toString());
				if (velocityX > 0) { // the player is moving to the right
					System.out.println("Moving right!");
					maxX = getCollisionShape().getMaxX();
					otherMinX = otherEntity.getCollisionShape().getMinX();
					differenceX = maxX - otherMinX; // calculate the distance that we should subtract from players x value
				} else if (velocityX < 0) { // the player is moving to the left
					System.out.println("Moving left!");
					minX = getCollisionShape().getMinX();
					otherMaxX = otherEntity.getCollisionShape().getMaxX();
					differenceX = minX - otherMaxX; // calculate the distance that we should subtract from players x value
				}
				
				if (velocityY > 0) { // the player is moving down
					System.out.println("Moving down!");
					maxY = getCollisionShape().getMaxY();
					otherMinY = otherEntity.getCollisionShape().getMinY();
					differenceY = maxY - otherMinY; // calculate the distance that we should subtract from players x value
				} else if (velocityY < 0) { // the player is moving up
					System.out.println("Moving up!");
					minY = getCollisionShape().getMinY();
					otherMaxY = otherEntity.getCollisionShape().getMaxY();
					differenceY = minY - otherMaxY; // calculate the distance that we should subtract from players x value
				}
				
				System.out.println("maxX: " + maxX);
				System.out.println("maxY: " + maxY);
				System.out.println("minX: " + minX);
				System.out.println("minY: " + minY);
				System.out.println("otherMaxX: " + otherMaxX);
				System.out.println("otherMaxY: " + otherMaxY);
				System.out.println("otherMinX: " + otherMinX);
				System.out.println("otherMinY: " + otherMinY);
				System.out.println("differenceX: " + differenceX + ", differenceY: " + differenceY);
				
				System.out.println("New Pos! x: " + (getX() - differenceX) + ", y: " + (getY() - differenceY));
				setX(getX() - differenceX); // reduce the players x value by the difference between player and otherEntity
				setY(getY() - differenceY); // and the y value too
			}
		} 
	} */
}
