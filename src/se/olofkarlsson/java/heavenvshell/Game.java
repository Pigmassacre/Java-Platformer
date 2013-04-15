package se.olofkarlsson.java.heavenvshell;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import se.olofkarlsson.java.heavenvshell.Entities.Player;
import se.olofkarlsson.java.heavenvshell.Weapons.RangedWeapons.Bow;

public class Game extends BasicGameState {

	int accTime;
	Input input;
	float gravity;
	Player player;
	Bow bow;

	public int getID() {
		return Main.GAME_STATE;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		player = new Player("res/player/base.png");
		bow = new Bow("res/player/bow.png");
		
		player.setX(Main.GAME_WINDOW_SIZE_X / 2);
		player.setY(Main.GAME_WINDOW_SIZE_Y / 2);

		gravity = 0.4f;
		
		input = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		accTime += delta;

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
				//bow.shoot();
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
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO
		/* I'm thinking the render method should work something like this:
		 * 
		 * renderBackground();
		 * renderEntities();
		 * renderGUI();
		 * 
		 * But for now, I'll just render each thing separately here.
		 */
		
		player.draw(player.getX(), player.getY());
		bow.draw(player.getX() + 16, player.getY() + 0);
	}
}