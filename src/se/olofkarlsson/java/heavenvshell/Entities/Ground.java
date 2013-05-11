package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.olofkarlsson.java.heavenvshell.GameworldEntities;
import se.olofkarlsson.java.heavenvshell.Entities.Core.CollisionEntity;

public class Ground extends CollisionEntity {

	public Ground(String sprite, float posX, float posY) throws SlickException {
		this.sprite = new Image(sprite);

		setX(posX);
		setY(posY);

		this.setupCollisionShape(getX(), getY(), 32, 32);
		GameworldEntities.gameworldEntities.add(this);
	}

	public void draw() {
		sprite.draw(getX(), getY());
	}

	public void update() {
		setCollisionShapeX(getX());
		setCollisionShapeY(getY());
	}
}
