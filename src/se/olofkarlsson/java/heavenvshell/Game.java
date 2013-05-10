package se.olofkarlsson.java.heavenvshell;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.olofkarlsson.java.heavenvshell.Entities.*;
import se.olofkarlsson.java.heavenvshell.Weapons.RangedWeapons.*;

public class Game extends BasicGameState {

	int accTime;
	Input input;
	float gravity;
	Player player;
	Ground ground;

	public int getID() {
		return Main.GAME_STATE;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		player = new Player("res/player/base.png");
		ground = new Ground("res/environment/ground-grass.png");

		player.setX(gc.getWidth() / 2);
		player.setY(gc.getHeight() / 2);
		
		ground.setX(gc.getWidth() / 2);
		ground.setY((gc.getHeight() / 2) + 100);

		gravity = 0.4f;

		input = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		accTime += delta;

		player.getCollisionShape().setX(player.getX());
		player.getCollisionShape().setY(player.getY());
		
		ground.getCollisionShape().setX(ground.getX());
		ground.getCollisionShape().setY(ground.getY());
		
		while (accTime > 0) {
			accTime -= 20;
			player.velocityY -= gravity;

			if (input.isKeyDown(Input.KEY_LEFT)) {
				player.setX(player.getX() - player.movementSpeed);
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				player.setX(player.getX() + player.movementSpeed);
			}
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				// bow.shoot();
			}
			if (input.isKeyPressed(Input.KEY_RALT)) {
				player.velocityY = 8.0f;
				player.jumping = true;
			}
			if (player.jumping) {
				player.setY(player.getY() - player.velocityY);
			}
		}

		checkCollision();

	}

	private void checkCollision() {
		// TODO I don't think this should be handled this way.
		if (player.getCollisionShape().intersects(ground.getCollisionShape())) {
			player.velocityY = 0f;
			System.out.println("COLLIDE");
			System.out.println("player X = " + player.getCollisionShape().getX() + " and ground X = " + ground.getCollisionShape().getX());
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO
		/*
		 * I'm thinking the render method should work something like this:
		 * 
		 * renderBackground(); renderEntities(); renderGUI();
		 * 
		 * But for now, I'll just render each thing separately here.
		 */

		player.draw();
		ground.draw();

	}
}