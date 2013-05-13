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

		acceleration = 0.5f;
		deceleration = 2f;

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

		newCheckCollision(newX, newY);
		
		setX(newX);
		setY(newY);

		//System.out.println("newX: " + newX + ", newY: " + newY);

		// System.out.println("Player pos: " + getX() + " " + getY()
		// + " and inAir: " + inAir);
	}

	public void moveLeft() {
		if (velocityX > -maxSpeedX) {
			velocityX -= acceleration;
		} else {
			velocityX = -maxSpeedX;
		}
	}

	public void moveRight() {
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

	public void newCheckCollision(float newX, float newY) {
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
					}
				}
			}
		}
	}

	public void oldCheckCollision(float newX, float newY) {
		CollisionEntity otherEntity = null;

		setCollisionShapeX(newX); // first check x-axis

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				System.out.println("Collision on x-axis! velocityX "
						+ velocityX);

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
						newX = otherEntity.getCollisionShape().getMaxX();
					}
				} else {
					if (!(wy > -hx)) {
						/* left side of B hit */
						newX = otherEntity.getCollisionShape().getMinX()
								- getCollisionShape().getWidth();
					}
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
				System.out.println("Collision on y-axis! velocityY: "
						+ velocityY);

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
						newY = otherEntity.getCollisionShape().getMinY()
								- getCollisionShape().getHeight();

					}
				} else {
					if (wy > -hx) {
						/* top side of B hit */
						newY = otherEntity.getCollisionShape().getMaxY();
					}
				}

				inAir = false;
				velocityY = 0f;
			}
		}

		setY(newY); // update y position now
		setCollisionShapeY(newY); // re-adjust collision box again
	}

}
