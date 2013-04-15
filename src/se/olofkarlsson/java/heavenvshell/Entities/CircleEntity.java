package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.geom.Circle;

public class CircleEntity extends Entity {

	private Circle collisionShape;

	public CircleEntity() {
		setupCollisionShape(16);
	}

	public CircleEntity(float radius) {
		setupCollisionShape(radius);
	}

	private void setupCollisionShape(float radius) {
		collisionShape = new Circle(getX(), getY(), radius);
	}

	public Circle getCollisionShape() {
		return collisionShape;
	}
}
