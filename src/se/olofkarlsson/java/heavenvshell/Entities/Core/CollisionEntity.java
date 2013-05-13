package se.olofkarlsson.java.heavenvshell.Entities.Core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class CollisionEntity extends Entity {

	private Rectangle collisionShape;
	public Graphics debugGraphics;

	public CollisionEntity() {
	}

	/**
	 * Create a CollisionShape at the given position, with given width and
	 * height.
	 * 
	 * @param x	the x position to create the shape at
	 * @param y the y position to create the shape at
	 * @param width the width of the shape
	 * @param height the height of the shape
	 */
	public void setupCollisionShape(float x, float y, float width, float height) {
		debugGraphics = new Graphics();
		collisionShape = new Rectangle(x, y, width, height);
	}

	public Rectangle getCollisionShape() {
		return collisionShape;
	}

	public void setCollisionShapeX(float x) {
		collisionShape.setX(x);
	}

	public void setCollisionShapeY(float y) {
		collisionShape.setY(y);
	}

	public void collidedWithGround() {

	}

	public void checkCollision(float newX, float newY) {

	}

	public float collidedOnXAxis(float newX, CollisionEntity otherEntity) {
		return 0f;
	}

	public float collidedOnYAxis(float newY, CollisionEntity otherEntity) {
		return 0f;
	}

	public void draw() {
		debugGraphics.draw(collisionShape);
	}

}
