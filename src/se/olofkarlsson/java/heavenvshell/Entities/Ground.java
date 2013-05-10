package se.olofkarlsson.java.heavenvshell.Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ground extends CollisionEntity {

	public Image sprite;

	public Ground(String sprite, float posX, float posY) throws SlickException {
		this.sprite = new Image(sprite);

		setX(posX);
		setY(posY);

		this.setupCollisionShape(getX(), getY(), 32, 32);
	}

	public void draw() {
		sprite.draw(getX(), getY());
	}

	public void update() {
		setCollisionShapeX(getX());
		setCollisionShapeY(getY());
	}
}
