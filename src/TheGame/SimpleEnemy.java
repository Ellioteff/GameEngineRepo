package TheGame;

import java.util.Random;

import GameEngine.DynamicSprite;
import GameEngine.Loader;
import GameEngine.Physics;

public class SimpleEnemy extends DynamicSprite {
	Random rand = new Random();

	public SimpleEnemy(int x, int y) {
		super(50, 50, x, y, Loader.loadImage("assets/sprites/kungen.png"));
		xVelocity = 0;
	}
	//same jump and actoncollision methods that are used by the player.
	@Override
	public void actOnCollision(DynamicSprite s) {
		if (s.isSolid())
			stepBackFromCollision();
	}
	
	public void jump() {
		if (onGround) {
			yVelocity = -2;
			onGround = false;
		}
	}

	//simple move method that randomly calls the jump method.
	@Override
	public void move() {
		super.move();
		if (!onGround) {
			yVelocity = Physics.applyGravity(yVelocity);
		} else {
			int i  = rand.nextInt(100);
			if(i == 20)
				jump();
			xVelocity = -1;
		}
		
	}

}
