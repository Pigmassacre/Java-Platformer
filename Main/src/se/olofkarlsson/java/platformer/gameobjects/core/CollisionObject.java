package se.olofkarlsson.java.platformer.gameobjects.core;

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
