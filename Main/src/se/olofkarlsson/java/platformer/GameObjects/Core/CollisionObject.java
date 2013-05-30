package se.olofkarlsson.java.platformer.GameObjects.Core;

public interface CollisionObject {

	void setupCollisionObject();

	void getCollisionObject();

	void xAxisCollision();

	void yAxisCollision();
	
	void collidedLeft();

	void collidedRight();

	void collidedTop();

	void collidedDown();
	
}
