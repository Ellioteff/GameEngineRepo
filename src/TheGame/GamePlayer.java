package TheGame;

import GameEngine.DynamicSprite;
import GameEngine.Loader;
import GameEngine.PlayerSprite;
import GameEngine.SoundObject;

public class GamePlayer extends PlayerSprite {
	private static final long serialVersionUID = 1L;
	SoundObject boing = new SoundObject(Loader.loadSound("assets/sounds/61847__simon-rue__boink-v3.wav"));

	//calls the super constructor and adds 2 hitboxes to the player.
	public GamePlayer(int x, int y) {
		super(50,50 ,x, y, "assets/sprites/silvia.png");
		this.addHitbox(15, 0, 20, 50);
		this.addHitbox(0, 40, 50, 10);
	}

	//if the enemy is of instance simpleenemy the player dies on collision.
	@Override
	public void actOnCollision(DynamicSprite s){
		super.actOnCollision(s);
		if( s instanceof SimpleEnemy)
			health = 0;
	}
	
	//adds soundeffect when jumping and then calls the super jump method.
	@Override
	public void jump() {
		if(onGround)
			boing.playSound();
		super.jump();
		
	}

}
