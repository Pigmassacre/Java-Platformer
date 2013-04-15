package se.olofkarlsson.java.testing.Entity;

public class StatEntity extends RectangleEntity {

	private int healthPoints;
	private int manaPoints;
	private int level;
	
	public StatEntity() {
	}
	
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
