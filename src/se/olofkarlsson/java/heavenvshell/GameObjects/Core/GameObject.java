package se.olofkarlsson.java.heavenvshell.GameObjects.Core;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * This class is meant to act as the base for each entity in the game. A unit
 * can be anything from the player, an enemy or a powerup. Basically, any object
 * that is represented in the game world via graphics.
 * 
 * @author Pigmassacre
 * 
 */
public abstract class GameObject {

	public Image sprite;
	
	private float x;
	private float y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void update(Input input, float gravity) {
	}

	public void draw() {
	}

}
