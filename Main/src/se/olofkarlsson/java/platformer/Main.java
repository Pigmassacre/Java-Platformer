package se.olofkarlsson.java.platformer;

import com.badlogic.gdx.Game;

public class Main extends Game {
	public static final int SPLASH_STATE = 1;
	public static final int MENU_STATE = 2;
	public static final int GAME_STATE = 3;
	public static final int PAUSED_STATE = 4;

	// Settings go here
	public static int GAME_WINDOW_SIZE_X = 800;
	public static int GAME_WINDOW_SIZE_Y = 600;

    @Override
	public void create() {

    }
     /*
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Splash());
		addState(new Game());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main());
		app.setDisplayMode(GAME_WINDOW_SIZE_X, GAME_WINDOW_SIZE_Y, false);
		app.start();
	}  */
}