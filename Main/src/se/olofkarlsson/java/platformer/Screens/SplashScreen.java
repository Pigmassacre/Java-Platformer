package se.olofkarlsson.java.platformer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import se.olofkarlsson.java.platformer.GameLoop;

public class SplashScreen extends AbstractScreen {

    private Texture splashTexture;
    private TextureRegion splashTextureRegion;

    public SplashScreen(GameLoop game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        splashTexture = new Texture("splash/pigmassacrestudios_logo.png");


        splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        splashTextureRegion = new TextureRegion(splashTexture, 0, 0, 800, 640);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        batch.draw(splashTextureRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        splashTexture.dispose();
    }

}
