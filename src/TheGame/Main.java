package TheGame;
import GameEngine.DynamicSprite;
import GameEngine.Game;
import GameEngine.GameObject;

public class Main {

	public static void main(String[] args) {
		
		Game game = new Game("Window", 1240, 880);
		game.addGameObject(new GameObject(new SilviaSprite()));
		game.run();
	}

}

