package TheGame;

import java.awt.event.KeyEvent;

import GameEngine.*;

public class Main {

	public static void main(String[] args) {

		GamePlayer ps = new GamePlayer(50, 50);
		PlayerObject po = new PlayerObject(ps);

		Game game = new Game("Best Game EU", 1240, 880, po);

		game.bindKeyPressed(KeyEvent.VK_LEFT, new KeyBinding(() -> {
			ps.setLeftDown();
		}));
		game.bindKeyPressed(KeyEvent.VK_RIGHT, new KeyBinding(() -> {
			ps.setRightDown();
		}));

		game.bindKeyReleased(KeyEvent.VK_LEFT, new KeyBinding(() -> {
			ps.setLeftUp();
		}));
		game.bindKeyReleased(KeyEvent.VK_RIGHT, new KeyBinding(() -> {
			ps.setRightUp();
		}));
		game.bindKeyPressed(KeyEvent.VK_SPACE, new KeyBinding(() -> {
			ps.jump();
		}));
		game.bindKeyPressed(KeyEvent.VK_S, new KeyBinding(() -> {
			Loader.saveGame(game, "thegamesave.game");
		}));
		game.bindKeyPressed(KeyEvent.VK_L, new KeyBinding(() -> {
			Loader.loadGame(game, "thegamesave.game");
		}));

		game.addGameObject(new GameObject(new Ground(0, 780)));
		game.addGameObject(new GameObject(new SimpleEnemy(1000, -1500)));
		game.addGameObject(new GameObject(new SimpleEnemy(1000, -500)));
		game.addGameObject(new GameObject(new SimpleEnemy(1000, 500)));




		game.run();
	}

}
