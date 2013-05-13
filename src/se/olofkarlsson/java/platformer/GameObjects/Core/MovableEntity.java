package se.olofkarlsson.java.platformer.GameObjects.Core;

public abstract class MovableEntity extends CollisionEntity {

	public float velocityX;
	public float velocityY;

	public float maxSpeedX;
	public float maxSpeedY;

	public float acceleration;
	public float deceleration;

}
