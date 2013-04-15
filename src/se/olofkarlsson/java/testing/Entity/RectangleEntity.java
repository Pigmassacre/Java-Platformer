package se.olofkarlsson.java.testing.Entity;

import org.newdawn.slick.geom.Rectangle;

public class RectangleEntity extends Entity {

	private Rectangle collisionShape;

	public RectangleEntity() {
		setupCollisionShape(32, 32);
	}

	public RectangleEntity(float width, float height) {
		setupCollisionShape(width, height);
	}

	private void setupCollisionShape(float width, float height) {
		collisionShape = new Rectangle(getX(), getY(), width, height);
	}

	public Rectangle getCollisionShape() {
		return collisionShape;
	}
}
