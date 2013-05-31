package se.olofkarlsson.java.platformer.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import se.olofkarlsson.java.platformer.GameLoop;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen extends AbstractScreen {

    private Texture splashTexture;
    private TextureRegion splashTextureRegion;
    private Image splashImage;

    public SplashScreen(GameLoop game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        splashTexture = new Texture("splash/pigmassacrestudios_logo.png");

        splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        splashTextureRegion = new TextureRegion(splashTexture, 0, 0, 800, 640);

        Drawable splashDrawable = new TextureRegionDrawable(splashTextureRegion);

        splashImage = new Image(splashDrawable, Scaling.stretch);
        splashImage.setFillParent(true);

        splashImage.getColor().a = 0f;

        splashImage.addAction(sequence(fadeIn(0.75f), delay(1.75f), fadeOut(0.75f),
            new Action() {
                @Override
                public boolean act(float delta) {
                    game.setScreen(new MenuScreen(game));
                    return true;
                }
            }));

        stage.addActor(splashImage);
    }

    @Override
    public void dispose() {
        super.dispose();
        splashTexture.dispose();
    }

}
