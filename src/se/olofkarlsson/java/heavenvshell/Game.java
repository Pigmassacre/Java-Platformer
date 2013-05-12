package se.olofkarlsson.java.heavenvshell;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMapPlus;

import se.olofkarlsson.java.heavenvshell.Entities.Player.Player;

public class Game extends BasicGameState {

	int accTime;
	Input input;
	float gravity;
	Player player;
	TiledMapPlus levelMap;

	public int getID() {
		return Main.GAME_STATE;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		levelMap = new TiledMapPlus("res/levels/test/testmap.tmx");
		
		GameworldEntities.initGameworld();
		
		GameworldEntities.setupGameworld(levelMap);

		player = new Player("res/player/base.png", 64, 0);

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
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		levelMap.render(0, 0);
		//levelMap.render(0, 0, 8, 0, 32, 32);
		
		for (int i = 0; i < GameworldEntities.entities.size(); i++) {
			GameworldEntities.entities.get(i).draw();
		}
		
		for (int i = 0; i < GameworldEntities.entitiesMovable.size(); i++) {
			GameworldEntities.entitiesMovable.get(i).draw();
		}
		
		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			GameworldEntities.geometryCollision.get(i).draw();
		}
	}

}