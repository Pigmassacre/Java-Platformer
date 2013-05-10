package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.geom.Rectangle;

public class CollisionEntity extends Entity {

	private Rectangle collisionShape;

	public CollisionEntity() {
	}

	public void setupCollisionShape(float x, float y, float width, float height) {
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

}
