package se.olofkarlsson.java.heavenvshell;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.tiled.Tile;

import se.olofkarlsson.java.heavenvshell.Entities.Core.Entity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.MovableEntity;

public class GameworldEntities {

	public static List<Entity> entities;
	public static List<Tile> geometry;
	public static List<Tile> geometryCollision;
	public static List<MovableEntity> entitiesMovable;

	public GameworldEntities() {

	}

	public static void setupGameworld() {
		entities = new ArrayList<Entity>();
		geometry = new ArrayList<Tile>();
		entitiesMovable = new ArrayList<MovableEntity>();
	}

}
