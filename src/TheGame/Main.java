package TheGame;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import GameEngine.DynamicSprite;
import GameEngine.Game;
import GameEngine.GameObject;
import GameEngine.KeyHandler;
import GameEngine.Loader;

public class Main {

	public static void main(String[] args) {
		
		Game game = new Game("Window", 1240, 880);
		
		new KeyHandler("Escape", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));		
		new KeyHandler("Up",KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)); 
		new KeyHandler("Down", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		new KeyHandler("Left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
		new KeyHandler("Right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
		new KeyHandler("Space", KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
		new KeyHandler("Return", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		
		SilviaSprite s1 = new SilviaSprite(50,0);
		s1.addHitbox(20,0,30,10);
		game.addGameObject(new GameObject(s1));
		
		SilviaSprite s = new SilviaSprite(85,50);
		s.addHitbox(0,0,20,20);
		game.addGameObject(new GameObject(s));
		
		game.run();
	}

}

