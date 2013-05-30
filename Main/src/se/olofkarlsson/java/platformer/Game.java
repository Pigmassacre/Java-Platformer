package se.olofkarlsson.java.platformer;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import se.olofkarlsson.java.platformer.GameObjects.Player.Player;

public class GameLoop extends Game {

    public static final string LOG = GameLoop.class.getSimpleName();

    private FPSLogger fpsLogger;

	int accTime;
	Input input;
	float gravity;
	Player player;
	Camera camera;

    @Override
	public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        camera.drawMap();
        camera.translateGraphics();

        for (int i = 0; i < GameworldEntities.entities.size(); i++) {
            GameworldEntities.entities.get(i).draw();
        }

        for (int i = 0; i < GameworldEntities.entitiesMovable.size(); i++) {
            GameworldEntities.entitiesMovable.get(i).draw();
        }

        for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
            GameworldEntities.geometryCollision.get(i).draw();
        }

        camera.untranslateGraphics();

        g.drawRect((gc.getWidth() / 2) - 16, (gc.getHeight() / 2) - 16, 32, 32);

        fpsLogger.log();
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		levelMap = new TiledMapPlus("res/levels/test/testmap.tmx");
		
		camera = new Camera(gc, levelMap);

		GameworldEntities.initGameworld();

		GameworldEntities.setupGameworld(levelMap);

		player = new Player("res/player/base.png", 64, 64);

		gravity = 0.4f;

		input = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		accTime += delta;

		while (accTime > 0) {
			accTime -= 20;
			for (int i = 0; i < GameworldEntities.entities.size(); i++) {
				GameworldEntities.entities.get(i).update(input, gravity);
			}

			for (int i = 0; i < GameworldEntities.entitiesMovable.size(); i++) {
				GameworldEntities.entitiesMovable.get(i).update(input, gravity);
			}
		}
		
		camera.centerOn(player.getX() + (player.getCollisionShape().getWidth() / 2), player.getY() + (player.getCollisionShape().getHeight() / 2));
	}
	
}