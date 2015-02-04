package GameEngine;

import java.awt.Rectangle;

public class PlayerSprite extends DynamicSprite {

	private static final long serialVersionUID = 1L;
	SoundObject sound = new SoundObject(Loader.loadSound("assets/sounds/Transformer Stingers 01 A.wav"));
	SoundObject boing = new SoundObject(Loader.loadSound("assets/sounds/61847__simon-rue__boink-v3.wav"));
	int incrementX = 1;
	int incrementY = 1;
	boolean rightDown = false;
	boolean leftDown = false;
	int loopCounter = 0;

	public PlayerSprite(int x, int y, String s) {
		super(50, 50, x, y, Loader.loadImage(s));
		xVelocity = 2;
		this.addHitbox(15, 0, 20, 50);
		this.addHitbox(0, 40, 50, 10);

	}

	@Override
	public void actOnCollision(DynamicSprite s) {
		if (!onGround) {

			// boing.playSound();
		}
		if (s.isSolid())
			super.stepBackFromCollision();
	}

	public void jump() {
		System.out.println("hoppar");
		if (onGround){
			yVelocity = -2;
			boing.playSound();
			onGround = false;
		}
	}

	@Override
	public void move() {
		loopCounter++;
		if (loopCounter % 100 == 0)
			jump();
		if(loopCounter % 500 == 0)
			xVelocity = 1;
		this.setPrevious();
		xPos += xVelocity * incrementX;
		yPos += yVelocity;
		if(onGround)
			xVelocity = Physics.applyAirResistance(xVelocity, 10);
		else
			xVelocity = Physics.applyAirResistance(xVelocity, 2);

		if (!onGround)
			yVelocity = Physics.applyGravity(yVelocity);

		// if (yPos > 800) {
		// if (yVelocity > 0.2) {
		// yVelocity = -(yVelocity * 0.9 - 0.2);
		// // boing.playSound();
		// } else {
		// // boing.playSound();
		// yVelocity = 0;
		// }
		// }
		// if (yPos > 800)
		// yPos = 800;
		//
		// if (xPos < 1 || xPos >= 1190) {
		// incrementX *= (-1);
		// }
	}

}
