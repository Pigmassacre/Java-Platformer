package se.olofkarlsson.java.testing;

import org.newdawn.slick.geom.*;

/**
 * An extension of entity that provides basic collision detection with another
 * CollisionEntity.
 * 
 * @author Pigmassacre
 * 
 */
public class CollisionEntity extends Entity {

	private float boundRadius;

	private Shape collisionShape

	/**
	 * Creates a CollisionEntity with a default boundRadius of 16 (half of tile
	 * size)
	 * 
	 * @return CollisionEntity A new CollisionEntity with boundRadius 16.
	 */
	public CollisionEntity() {
		setBoundRadius(16);
		createCollisionShape();
	}

	/**
	 * Creates a CollisionEntity with a set boundRadius.
	 * 
	 * @param newRadius
	 *            The new radius of the CollisionEntity
	 * @return CollisionEntity A new CollisionEntity with boundRadius newRadius.
	 */
	public CollisionEntity(float newRadius) {
		setBoundRadius(newRadius);
		createCollisionShape();
	}

	private void createCollisionShape() {
		collisionShape = new Circle(getX(), getY(), getBoundRadius());
	}
	
	public Circ

	public float getBoundRadius() {
		return boundRadius;
	}

	public void setBoundRadius(float boundRadius) {
		this.boundRadius = boundRadius;
	}

	/**
	 * Checks if two CollisionEntities collide with each other.
	 * 
	 * @param e1
	 * @param e2
	 * @return True if the given entities collide with each other, False if not
	 */
	public boolean collides(CollisionEntity collidingEntity) {
		/*float xDistance = e1.getX() - e2.getX(), yDistance = e1.getY()
				- e2.getY(), rSum = e1.getBoundRadius() + e2.getBoundRadius();
		return xDistance * xDistance + yDistance * yDistance < rSum * rSum;*/
		collisionCircle.intersects(collidingEntity)
	}
}
