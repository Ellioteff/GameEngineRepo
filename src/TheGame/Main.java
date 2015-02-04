package TheGame;

import java.awt.event.KeyEvent;
import GameEngine.PlayerObject;
import GameEngine.PlayerSprite;
import GameEngine.*;
import GameEngine.Game.Key;

public class Main {

	public static void main(String[] args) {

		PlayerSprite ps = new PlayerSprite(50, 50, "assets/sprites/Silvia.png");
		PlayerObject po = new PlayerObject(ps);
		Game game = new Game("Window", 1240, 880, po);

		game.bind(KeyEvent.VK_UP, Key.up);
		game.bind(KeyEvent.VK_LEFT, Key.left);
		game.bind(KeyEvent.VK_DOWN, Key.down);
		game.bind(KeyEvent.VK_RIGHT, Key.right);
		game.bind(KeyEvent.VK_SPACE, Key.space);

		
		game.addGameObject(new GameObject(new Ground()));

		game.run();
	}

}
