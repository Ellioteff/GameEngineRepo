package GameEngine;

public class Main {

	public static void main(String[] args) {
		
		Game game = new Game("Window", 1240, 880);
		game.addSprite(new DynamicSprite(0,0,50,50,"assets/Silvia.png"));
		game.run();
	}

}
