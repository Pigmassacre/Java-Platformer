package se.olofkarlsson.java.heavenvshell.Weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.olofkarlsson.java.heavenvshell.Entities.Core.Entity;

public class Weapon extends Entity {

	protected Image idle;

	public Weapon() {
		// TODO What should be here?
	}

	public void setImage(String imagePath) throws SlickException {
		idle = new Image(imagePath);
		// TODO I don't think this should be done like this.
	}

	public void attack() {
		// TODO A weapon can attack.
	}

	public void draw(float x, float y) {
		idle.draw(x, y);
	}

}
