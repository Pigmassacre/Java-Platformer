package se.olofkarlsson.java.platformer;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import se.olofkarlsson.java.platformer.screens.SplashScreen;

public class GameLoop extends Game {

    public static final String LOG = GameLoop.class.getSimpleName();

    private FPSLogger fpsLogger;

    public SplashScreen getSplashScreen() {
        return new SplashScreen(this);
    }

    @Override
	public void create() {
        Gdx.app.log(GameLoop.LOG, "Creating game.");
        fpsLogger = new FPSLogger();
        setScreen(getSplashScreen());
    }

    @Override
    public void resize(int width, int height) {

        Gdx.app.log(GameLoop.LOG, "Resizing window to: " + width + "x" + height);
    }

    @Override
    public void render() {
        super.render();

        fpsLogger.log();
    }

    public void pause() {
        Gdx.app.log(GameLoop.LOG, "Pausing game.");
    }

    public void resume() {
        Gdx.app.log(GameLoop.LOG, "Resuming game.");
    }

    public void dispose() {
        Gdx.app.log(GameLoop.LOG, "Disposing game.");
    }
	
}