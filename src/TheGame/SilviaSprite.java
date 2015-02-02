package TheGame;

import java.awt.Rectangle;

import GameEngine.DynamicSprite;
import GameEngine.Loader;
import GameEngine.Physics;
import GameEngine.SoundObject;

public class SilviaSprite extends DynamicSprite {

	private static final long serialVersionUID = 1L;
	SoundObject sound = new SoundObject(Loader.loadSound("assets/sounds/Transformer Stingers 01 A.wav"));
	SoundObject boing = new SoundObject(Loader.loadSound("assets/sounds/61847__simon-rue__boink-v3.wav"));
	int incrementX = 1;
	int incrementY = 1;

	public SilviaSprite(int x, int y) {
		super(50, 50, x, y, Loader.loadImage("assets/sprites/Silvia.png"));
		xVelocity = 2;
		
	}

	@Override
	public void move() {
		xPos += xVelocity * incrementX;
		yPos += yVelocity;		
		xVelocity = Physics.applyAirResistance(xVelocity, 10);
		if (yPos < 790)
			yVelocity = Physics.applyGravity(yVelocity);

		if (yPos > 800) {
			if (yVelocity > 0.2) {
				yVelocity = -(yVelocity * 0.9 - 0.2);
				boing.playSound();
			} else {
				boing.playSound();
				yVelocity = 0;
			}
		}
		if (yPos > 800)
			yPos = 800;

		if (xPos < 1 || xPos >= 1190) {
			incrementX *= (-1);
		}
	}

}
