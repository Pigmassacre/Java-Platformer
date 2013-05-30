package se.olofkarlsson.java.platformer;

import java.util.ArrayList;
import java.util.List;

import se.olofkarlsson.java.platformer.GameObjects.Core.CollisionEntity;
import se.olofkarlsson.java.platformer.GameObjects.Core.GameObject;
import se.olofkarlsson.java.platformer.GameObjects.Core.MovableEntity;

public class GameworldEntities {

	public static List<GameObject> entities;
	public static List<Tile> geometry;
	public static List<CollisionEntity> geometryCollision;
	public static List<MovableEntity> entitiesMovable;

	public GameworldEntities() {

	}

	public static void initGameworld() {
		entities = new ArrayList<GameObject>();
		entitiesMovable = new ArrayList<MovableEntity>();
		geometry = new ArrayList<Tile>();
		geometryCollision = new ArrayList<CollisionEntity>();
	}

	public static void setupGameworld(TiledMapPlus levelMap) {
		CollisionEntity collisionEntity;
		int id;
		float x, y, width, height;
		for (int w = 0; w < levelMap.getWidth(); w++) {
			for (int h = 0; h < levelMap.getHeight(); h++) {
				id = levelMap.getTileId(w, h,
						levelMap.getLayerIndex("BaseLayer"));

				if (levelMap.getTileProperty(id, "mask", "false")
						.equals("true")) {
					if (levelMap.getTileProperty(id, "masktype", "null")
							.equals("rectangle")) {
						x = w * 32;
						y = h * 32;
						width = levelMap.getTileWidth();
						height = levelMap.getTileHeight();
						
						collisionEntity = new CollisionEntity();
						collisionEntity.setupCollisionShape(x, y, width, height);
						geometryCollision.add(collisionEntity);
						System.out.println("Added entity to collision at: " + x + " / " + y);
					}
				}
			}
		}
	}
}
