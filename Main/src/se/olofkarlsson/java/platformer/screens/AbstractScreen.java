package se.olofkarlsson.java.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import se.olofkarlsson.java.platformer.GameLoop;

public abstract class AbstractScreen implements Screen {

    protected final GameLoop game;
    protected final BitmapFont font;
    protected final SpriteBatch batch;
    protected final Stage stage;

    public AbstractScreen(GameLoop game) {
        this.game = game;
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.stage = new Stage(0, 0, true);
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    protected boolean isGameScreen() {
        return false;
    }

    @Override
    public void show() {
        Gdx.app.log(GameLoop.LOG, "Showing screen: " + getName());
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(GameLoop.LOG, "Resizing screen " + getName() + " to: " + width + "x" + height);

        stage.setViewport(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.app.log(GameLoop.LOG, "Hiding screen: " + getName());
    }

    @Override
    public void pause() {
        Gdx.app.log(GameLoop.LOG, "Pausing screen: " + getName());
    }

    @Override
    public void resume() {
        Gdx.app.log(GameLoop.LOG, "Resuming screen: " + getName());
    }

    @Override
    public void dispose() {
        Gdx.app.log(GameLoop.LOG, "Disposing screen: " + getName());

        stage.dispose();
        batch.dispose();
        font.dispose();
    }

}
