package se.olofkarlsson.java.heavenvshell;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.olofkarlsson.java.heavenvshell.Entities.*;
import se.olofkarlsson.java.heavenvshell.Entities.Core.Entity;
import se.olofkarlsson.java.heavenvshell.Entities.Core.MovableEntity;
import se.olofkarlsson.java.heavenvshell.Entities.Player.Player;

public class Game extends BasicGameState {

	int accTime;
	Input input;
	float gravity;
	Player player;
	Ground ground1, ground2, ground3;

	public int getID() {
		return Main.GAME_STATE;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		GameworldEntities.setupGameworld();

		player = new Player("res/player/base.png", gc.getWidth() / 2,
				gc.getHeight() / 2);
		ground1 = new Ground("res/environment/ground-grass.png",
				gc.getWidth() / 2, (gc.getHeight() / 2) + 100);
		ground2 = new Ground("res/environment/ground-grass.png",
				gc.getWidth() / 2 + 32, (gc.getHeight() / 2) + 100);
		ground3 = new Ground("res/environment/ground-grass.png",
				gc.getWidth() / 2 - 32, (gc.getHeight() / 2) + 100);

		gravity = 0.4f;

		//GameworldEntities.gameworldEntities.add(player);
		//GameworldEntities.gameworldEntities.add(ground1);
		//GameworldEntities.gameworldEntities.add(ground2);
		//GameworldEntities.gameworldEntities.add(ground3);

		//GameworldEntities.gameworldMovableEntities.add(player);

		input = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		accTime += delta;

		while (accTime > 0) {
			accTime -= 20;
			for (int i = 0; i < GameworldEntities.gameworldEntities.size(); i++) {
				GameworldEntities.gameworldEntities.get(i).update(input, gravity);
			}
		}

		checkCollision();

	}

	private void checkCollision() {
		MovableEntity entity;
		
		for (int i = 0; i < GameworldEntities.gameworldMovableEntities.size(); i++) {
			entity = GameworldEntities.gameworldMovableEntities.get(i);
			if (entity.getCollisionShape().intersects(
					ground1.getCollisionShape())) {
				entity.setY(ground1.getY() - ground1.getCollisionShape().getHeight());
				entity.collidedWithGround();
			} else if (entity.getCollisionShape().intersects(
					ground2.getCollisionShape())) {
				entity.setY(ground2.getY() - ground2.getCollisionShape().getHeight());
				entity.collidedWithGround();
			} else if (entity.getCollisionShape().intersects(
					ground3.getCollisionShape())) {
				entity.setY(ground3.getY() - ground3.getCollisionShape().getHeight());
				entity.collidedWithGround();
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		/*
		 * I'm thinking the render method should work something like this:
		 * 
		 * renderBackground(); renderEntities(); renderGUI();
		 * 
		 * But for now, I'll just render each thing separately here.
		 */

		for (Entity e : GameworldEntities.gameworldEntities) {
			e.draw();
		}

	}

}