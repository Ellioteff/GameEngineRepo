package TheGame;

import java.awt.Rectangle;

import GameEngine.DynamicSprite;
import GameEngine.Loader;
import GameEngine.Physics;
import GameEngine.SoundObject;
import GameEngine.Sprite;

public class SilviaSprite extends DynamicSprite {

	private static final long serialVersionUID = 1L;
	SoundObject sound = new SoundObject(Loader.loadSound("assets/sounds/Transformer Stingers 01 A.wav"));
	SoundObject boing = new SoundObject(Loader.loadSound("assets/sounds/61847__simon-rue__boink-v3.wav"));
	int incrementX = 1;
	int incrementY = 1;
	int loopCounter = 0;
	

	public SilviaSprite(int x, int y) {
		super(50, 50, x, y, Loader.loadImage("assets/sprites/Silvia.png"));
		xVelocity = 2;
		this.addHitbox(15, 0, 20, 50);
		this.addHitbox(0,40,50,10);

	}

	@Override
	public void actOnCollision(DynamicSprite s) {
		if (!onGround) {

			//boing.playSound();
		}
		if(s.isSolid())
			super.stepBackFromCollision();
	}

	@Override
	public void move() {
		loopCounter++;
		this.setPrevious();
		xPos += xVelocity * incrementX;
		yPos += yVelocity;
		xVelocity = Physics.applyAirResistance(xVelocity, 10);
		if (!onGround)
			yVelocity = Physics.applyGravity(yVelocity);

//		if (yPos > 800) {
//			if (yVelocity > 0.2) {
//				yVelocity = -(yVelocity * 0.9 - 0.2);
//				// boing.playSound();
//			} else {
//				// boing.playSound();
//				yVelocity = 0;
//			}
//		}
//		if (yPos > 800)
//			yPos = 800;
//
//		if (xPos < 1 || xPos >= 1190) {
//			incrementX *= (-1);
//		}
	}

}
