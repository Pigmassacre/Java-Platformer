package se.olofkarlsson.java.testing;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Movement {

	private int JUMP_KEY = Input.KEY_SPACE;

	public void checkMovement(GameContainer gc, int delta) {

		Input input = gc.getInput();

		if (input.isKeyDown(JUMP_KEY)) {

		}

	}

}
