package se.olofkarlsson.java.platformer.Weapons.RangedWeapons;

import org.newdawn.slick.SlickException;

import se.olofkarlsson.java.platformer.Weapons.RangedWeapon;

public class Bow extends RangedWeapon {

	public Bow(String imagePath) throws SlickException {
		// TODO This is a specific implementation of a ranged weapon.
		setImage(imagePath);
	}

	@Override
	public void attack() {
		// TODO Shoots an arrow, but probably handled by RangedWeapon.
	}

}
