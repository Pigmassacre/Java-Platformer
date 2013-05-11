package se.olofkarlsson.java.heavenvshell;


import java.util.ArrayList;
import java.util.List;

import se.olofkarlsson.java.heavenvshell.Entities.Core.Entity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.MovableEntity;

public class GameworldEntities {

	public static List<Entity> gameworldEntities;
	public static List<MovableEntity> gameworldMovableEntities;

	public GameworldEntities() {

	}

	public static void setupGameworld() {
		gameworldEntities = new ArrayList<Entity>();
		gameworldMovableEntities = new ArrayList<MovableEntity>();
	}

}
