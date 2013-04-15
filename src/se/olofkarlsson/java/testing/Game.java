package se.olofkarlsson.java.testing;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

	int accTime;
	Input input;
	boolean jumping;
	float playerX;
	float playerY;
	float movementSpeed;
	float velocityY;
	float gravity;

	Image player = null;

	public int getID() {
		return Main.GAME_STATE;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		player = new Image("res/player/base.png");

		playerX = Main.GAME_WINDOW_SIZE_X / 2;
		playerY = Main.GAME_WINDOW_SIZE_Y / 2;

		movementSpeed = 4;
		gravity = 0.4f;

		input = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		accTime += delta;

		while (accTime > 0) {
			accTime -= 20;
			velocityY -= gravity;

			if (input.isKeyDown(Input.KEY_LEFT)) {
				playerX -= movementSpeed;
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				playerX += movementSpeed;
			}
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				velocityY = 8.0f;
				jumping = true;
			}
			if (jumping) {
				playerY -= velocityY;
			}
		}
		
		checkCollision();

	}

	private void checkCollision() {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		player.draw(playerX, playerY);
	}
}