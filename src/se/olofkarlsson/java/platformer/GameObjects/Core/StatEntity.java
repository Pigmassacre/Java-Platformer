package se.olofkarlsson.java.platformer.GameObjects.Core;

public abstract class StatEntity extends CollisionEntity {

	private int healthPoints;
	private int manaPoints;
	private int level;

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getManaPoints() {
		return manaPoints;
	}

	public void setManaPoints(int ManaPoints) {
		this.manaPoints = ManaPoints;
	}

	public int getLevel() {
		return level;
	}

	public void setlevel(int level) {
		this.level = level;
	}
}
