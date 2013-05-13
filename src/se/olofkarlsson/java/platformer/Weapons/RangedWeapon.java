package se.olofkarlsson.java.platformer.Weapons;

public class RangedWeapon extends Weapon {

	public RangedWeapon() {
		// TODO Implement a framework for ranged weapons.
	}
	
	public void draw(float x, float y, float angle) {
		idle.setRotation(angle);
		idle.draw(x, y);
	}
	
}
