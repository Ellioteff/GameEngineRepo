package TheGame;

import java.awt.event.KeyEvent;

import GameEngine.*;

public class Main {

	public static void main(String[] args) {

		PlayerSprite ps = new PlayerSprite(50, 50, "assets/sprites/Silvia.png");
		PlayerObject po = new PlayerObject(ps);		
		
		Game game = new Game("Best Game EU", 1240, 880, po);
		
		game.bindKey(KeyEvent.VK_LEFT, new KeyBinding(() -> {
			ps.move();
		} ));
		game.bindKey(KeyEvent.VK_RIGHT, new KeyBinding(() -> {
			ps.move();
		} ));
		game.bindKey(KeyEvent.VK_SPACE, new KeyBinding(() -> {
			ps.jump();
		} ) );
		

		
		game.addGameObject(new GameObject(new Ground()));

		game.run();
	}

}
