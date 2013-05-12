package se.olofkarlsson.java.heavenvshell;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.tiled.Tile;
import org.newdawn.slick.tiled.TiledMapPlus;

import se.olofkarlsson.java.heavenvshell.Entities.Core.CollisionEntity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.Entity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.MovableEntity;

public class GameworldEntities {

	public static List<Entity> entities;
	public static List<Tile> geometry;
	public static List<CollisionEntity> geometryCollision;
	public static List<MovableEntity> entitiesMovable;

	public GameworldEntities() {

	}

	public static void initGameworld() {
		entities = new ArrayList<Entity>();
		entitiesMovable = new ArrayList<MovableEntity>();
		geometry = new ArrayList<Tile>();
		geometryCollision = new ArrayList<CollisionEntity>();
	}

	public static void setupGameworld(TiledMapPlus levelMap) {
		/*
		 * ArrayList<GroupObject> maskedObjects =
		 * levelMap.getObjectGroup("Mask") .getObjects(); GroupObject
		 * currentObject;CollisionEntity collisionEntity;
		 * 
		 * for (int i = 0; i < maskedObjects.size(); i++) { currentObject =
		 * maskedObjects.get(i); collisionEntity = new CollisionEntity();
		 * collisionEntity.setupCollisionShape(currentObject.x, currentObject.y,
		 * currentObject.width, currentObject.height); System.out.println("x: "
		 * + currentObject.x + " and y: " + currentObject.y + " and width: " +
		 * currentObject.width + " and height: " + currentObject.height);
		 * geometryCollision.add(collisionEntity); System.out.println("Added " +
		 * collisionEntity.toString() + " to geometryCollision, it has width: "
		 * + collisionEntity.getCollisionShape().getWidth() + " and height: " +
		 * collisionEntity.getCollisionShape().getHeight()); }
		 */
		CollisionEntity collisionEntity;
		int id;
		float x, y, width, height;
		// Tile currentTile;
		for (int w = 0; w < levelMap.getWidth(); w++) {
			//System.out.println("w: " + w);
			for (int h = 0; h < levelMap.getHeight(); h++) {
				//System.out.println("h: " + h);
				id = levelMap.getTileId(w, h,
						levelMap.getLayerIndex("BaseLayer"));
				// currentTile = levelMap.

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
