package TheGame;

import GameEngine.Loader;
import GameEngine.PlayerSprite;
import GameEngine.SoundObject;

public class GamePlayer extends PlayerSprite {
	private static final long serialVersionUID = 1L;
	SoundObject boing = new SoundObject(Loader.loadSound("assets/sounds/61847__simon-rue__boink-v3.wav"));

	public GamePlayer(int x, int y) {
		super(50,50 ,x, y, "assets/sprites/silvia.png");
		this.addHitbox(15, 0, 20, 50);
		this.addHitbox(0, 40, 50, 10);
	}

	@Override
	public void jump() {
		if(onGround)
			boing.playSound();
		super.jump();
		
	}

}
