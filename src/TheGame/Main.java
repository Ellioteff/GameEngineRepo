package TheGame;
import java.awt.Rectangle;

import GameEngine.DynamicSprite;
import GameEngine.Game;
import GameEngine.GameObject;
import GameEngine.Loader;

public class Main {

	public static void main(String[] args) {
		
		Game game = new Game("Window", 1240, 880);
		
		SilviaSprite s1 = new SilviaSprite(50,0);
		s1.addHitbox(20,0,30,10);
		game.addGameObject(new GameObject(s1));
		
		SilviaSprite s = new SilviaSprite(85,50);
		s.addHitbox(0,0,20,20);
		game.addGameObject(new GameObject(s));
		
		game.run();
	}

}

