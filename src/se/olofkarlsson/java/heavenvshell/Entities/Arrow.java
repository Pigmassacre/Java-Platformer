package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.olofkarlsson.java.heavenvshell.GameworldEntities;

public class Arrow extends Projectile {

	public Arrow(float x, float y, float velocityX, float velocityY) throws SlickException {
		this.sprite = new Image("res/weapons/projectiles/arrow-default.png");

		inAir = true;

		setX(x);
		setY(y);

		this.velocityX = velocityX;
		this.velocityY = velocityY;

		maxSpeedX = 32f;
		maxSpeedY = 16f;

		this.acceleration = 0.25f;
		this.deceleration = 0.5f;

		setupCollisionShape(getX(), getY(), 16, 8);
		GameworldEntities.gameworldEntities.add(this);
		GameworldEntities.gameworldMovableEntities.add(this);
	}
	
	public Arrow(float x, float y, float velocityX, float velocityY, float acceleration, float deceleration) throws SlickException {
		this.sprite = new Image("res/weapons/projectiles/arrow-default.png");

		inAir = true;

		setX(x);
		setY(y);

		this.velocityX = velocityX;
		this.velocityY = velocityY;

		maxSpeedX = 32f;
		maxSpeedY = 16f;

		this.acceleration = acceleration;
		this.deceleration = deceleration;

		setupCollisionShape(getX(), getY(), 16, 8);
		GameworldEntities.gameworldEntities.add(this);
		GameworldEntities.gameworldMovableEntities.add(this);
	}

}
