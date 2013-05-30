package se.olofkarlsson.java.platformer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Splash extends BasicGameState {

	int accTime;
	Image splashImage;

	public int getID() {
		return Main.SPLASH_STATE;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		splashImage = new Image(
				"res/pigmassacreproductions/pigmassacrestudios_logo.png");
		splashImage = splashImage.getScaledCopy(0.6f);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		splashImage.drawCentered(gc.getWidth() / 2, gc.getHeight() / 2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		accTime += delta;

		if (accTime > 0) { // makes splash not shown
			sbg.enterState(Main.GAME_STATE/* , leave, enter */);
		}

	}
}
