package se.olofkarlsson.java.heavenvshell.GameObjects.Player;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import se.olofkarlsson.java.heavenvshell.GameworldEntities;
import se.olofkarlsson.java.heavenvshell.GameObjects.Arrow;
import se.olofkarlsson.java.heavenvshell.GameObjects.Core.CollisionEntity;
import se.olofkarlsson.java.heavenvshell.GameObjects.Core.MovableEntity;

public class Player extends MovableEntity {

	private Random random = new Random();
	public Graphics debugGraphics;
	public boolean inAir;
	public Animation currentAnimation;
	public Animation standing;
	public Animation runningLeft;
	public Animation runningRight;
	public Animation falling;
	public Animation jumpingLeft;
	public Animation jumpingRight;
	public SpriteSheet standingSS;
	public SpriteSheet runningLeftSS;
	public SpriteSheet runningRightSS;
	public SpriteSheet fallingSS;
	public SpriteSheet jumpingRightSS;
	public SpriteSheet jumpingLeftSS;
	
	public Player(String sprite, float posX, float posY) throws SlickException {
		this.sprite = new Image(sprite);
		standingSS = new SpriteSheet("res/player/player-base-standing.png", 32, 32);
		runningLeftSS = new SpriteSheet("res/player/player-base-running-left.png", 32, 32);
		runningRightSS = new SpriteSheet("res/player/player-base-running-right.png", 32, 32);
		fallingSS = new SpriteSheet("res/player/player-base-falling.png", 32, 32);		
		jumpingLeftSS = new SpriteSheet("res/player/player-base-jumping-left.png", 32, 32);
		jumpingRightSS = new SpriteSheet("res/player/player-base-jumping-right.png", 32, 32);
		
		
		standing = new Animation(standingSS, 500);
		standing.setAutoUpdate(true);
		runningLeft = new Animation(runningLeftSS, 100);
		runningLeft.setAutoUpdate(true);
		runningRight = new Animation(runningRightSS, 100);
		runningRight.setAutoUpdate(true);
		falling = new Animation(fallingSS, 100);
		falling.setAutoUpdate(true);
		jumpingLeft = new Animation(jumpingLeftSS, 200);
		jumpingLeft.setAutoUpdate(true);
		jumpingRight = new Animation(jumpingRightSS, 200);
		jumpingRight.setAutoUpdate(true);
		
		currentAnimation = falling;

		debugGraphics = new Graphics();

		inAir = true;

		setX(posX);
		setY(posY);

		velocityX = 0f;
		velocityY = 0f;

		maxSpeedX = 4f;
		maxSpeedY = 12f;

		acceleration = 0.5f;
		deceleration = 2f;
		setupCollisionShape(getX(), getY(), 32, 32);
		GameworldEntities.entitiesMovable.add(this);
	}

	public void draw() {
		//sprite.draw(getX(), getY());
		currentAnimation.draw(getX(), getY());
		//debugGraphics.draw(getCollisionShape());
	}

	public void update(Input input, float gravity) {
		float newX, newY;

		if (input.isKeyDown(Input.KEY_LEFT)) {
			moveLeft();
		} else if (velocityX < 0) {
			velocityX += deceleration;
			if (velocityX > 0) {
				velocityX = 0;
			}
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			moveRight();
		} else if (velocityX > 0) {
			velocityX -= deceleration;
			if (velocityX < 0) {
				velocityX = 0;
			}
		}

		if (input.isKeyDown(Input.KEY_RALT)) {
			jump();
		}

		if (input.isKeyDown(Input.KEY_SPACE)) {
			shoot();
		}

		if (velocityY < maxSpeedY) {
			velocityY += gravity;
		} else {
			velocityY = maxSpeedY;
		}
		
		if (inAir) {
			if (velocityY > 0) { // falling down
				currentAnimation = falling;
			} else if (velocityY < 0) { // jumping up
				if (velocityX > 0) {
					currentAnimation = jumpingRight;
				} else if (velocityX < 0) {
					currentAnimation = jumpingLeft;
				}
			}
		}
		
		newX = getX() + velocityX; // this is where we want to go
		newY = getY() + velocityY;

		// handle collision

		checkCollision(newX, newY);

		// System.out.println("newX: " + newX + ", newY: " + newY);

		// System.out.println("Player pos: " + getX() + " " + getY()
		// + " and inAir: " + inAir);
	}

	public void moveLeft() {
		currentAnimation = runningLeft;
		
		if (velocityX > -maxSpeedX) {
			velocityX -= acceleration;
		} else {
			velocityX = -maxSpeedX;
		}
	}

	public void moveRight() {
		currentAnimation = runningRight;
		
		if (velocityX < maxSpeedX) {
			velocityX += acceleration;
		} else {
			velocityX = maxSpeedX;
		}
	}

	public void jump() {
		if (!inAir) {
			velocityY = -8f;
			inAir = true;
		}
	}

	public void shoot() {
		try {
			new Arrow(getX(), getY() + 16 * random.nextFloat(), 32f, 0f);
		} catch (SlickException e) {
			System.err.println("Failed to create projectile, exception: " + e);
		}
	}

	public void checkCollision(float newX, float newY) {
		CollisionEntity otherEntity = null;

		setCollisionShapeX(newX); // first check x-axis

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				System.out.println("Collision on x-axis! velocityX "
						+ velocityX);
				newX = collidedOnXAxis(newX, otherEntity);
			}
		}

		setX(newX); // update x position now
		setCollisionShapeX(newX); // re-adjust collision box

		setCollisionShapeY(newY); // now check y axis

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				System.out.println("Collision on y-axis! velocityY: "
						+ velocityY);
				newY = collidedOnYAxis(newY, otherEntity);
			}
		}

		setY(newY); // update y position now
		setCollisionShapeY(newY); // re-adjust collision box again
	}

	public float collidedOnXAxis(float newX, CollisionEntity otherEntity) {
		float wy = (getCollisionShape().getWidth() + otherEntity
				.getCollisionShape().getWidth())
				* (getCollisionShape().getCenterY() - otherEntity
						.getCollisionShape().getCenterY());
		float hx = (getCollisionShape().getHeight() + otherEntity
				.getCollisionShape().getHeight())
				* (getCollisionShape().getCenterX() - otherEntity
						.getCollisionShape().getCenterX());

		if (hx > wy) {
			if (wy > -hx) {
				/* right side of B hit */
				System.out.println("right");
				newX = otherEntity.getCollisionShape().getMaxX();
				velocityX = 0f;
			}
		} else {
			if (!(wy > -hx)) {
				/* left side of B hit */
				System.out.println("left");
				newX = otherEntity.getCollisionShape().getMinX()
						- getCollisionShape().getWidth();
				velocityX = 0f;
			}
		}

		return newX;
	}

	public float collidedOnYAxis(float newY, CollisionEntity otherEntity) {
		float wy = (getCollisionShape().getWidth() + otherEntity
				.getCollisionShape().getWidth())
				* (getCollisionShape().getCenterY() - otherEntity
						.getCollisionShape().getCenterY());
		float hx = (getCollisionShape().getHeight() + otherEntity
				.getCollisionShape().getHeight())
				* (getCollisionShape().getCenterX() - otherEntity
						.getCollisionShape().getCenterX());

		if (hx > wy) {
			if (!(wy > -hx)) {
				/* bottom side of B hit */
				System.out.println("bottom");
				newY = otherEntity.getCollisionShape().getMinY()
						- getCollisionShape().getHeight();
				collidedWithGround();
				velocityY = 0f;
			}
		} else {
			if (wy > -hx) {
				/* top side of B hit */
				System.out.println("top");
				newY = otherEntity.getCollisionShape().getMaxY();
				velocityY = 0f;
			}
		}

		return newY;
	}

	public void collidedWithGround() {
		if (inAir) {
			inAir = false;
			currentAnimation = standing;
		}
	}
	
	/*
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * NOT USED, staying here for now though
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	public void checkCollisionDeprecated(float newX, float newY) {
		CollisionEntity otherEntity = null;
		float wy, hx;

		setCollisionShapeX(newX);
		setCollisionShapeY(newY);

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				System.out.println("Collision! velocityX " + velocityX
						+ ", velocityY " + velocityY);

				wy = (getCollisionShape().getWidth() + otherEntity
						.getCollisionShape().getWidth())
						* (getCollisionShape().getCenterY() - otherEntity
								.getCollisionShape().getCenterY());
				hx = (getCollisionShape().getHeight() + otherEntity
						.getCollisionShape().getHeight())
						* (getCollisionShape().getCenterX() - otherEntity
								.getCollisionShape().getCenterX());

				if (wy > hx) {
					if (wy > -hx) {
						/* top */
						System.out.println("top");
						newY = otherEntity.getCollisionShape().getMaxY();
						velocityY = 0f;
					} else {
						/* left */
						System.out.println("left");
						newX = otherEntity.getCollisionShape().getMinX()
								- getCollisionShape().getWidth();
						velocityX = 0f;
					}
				} else {
					if (wy > -hx) {
						/* right */
						System.out.println("right");
						newX = otherEntity.getCollisionShape().getMaxX();
						velocityX = 0f;
					} else {
						/* bottom */
						System.out.println("bottom");
						newY = otherEntity.getCollisionShape().getMinY()
								- getCollisionShape().getHeight();
						velocityY = 0f;
						inAir = false;
					}
				}
			}
		}

		setX(newX);
		setY(newY);
	}

}
