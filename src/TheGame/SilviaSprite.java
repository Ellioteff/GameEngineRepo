package TheGame;

import GameEngine.DynamicSprite;
import GameEngine.Loader;
import GameEngine.SoundObject;

public class SilviaSprite extends DynamicSprite {

	private static final long serialVersionUID = 1L;
	SoundObject sound = new SoundObject(Loader.loadSound("assets/sounds/Transformer Stingers 01 A.wav"));
	SoundObject boing = new SoundObject(Loader.loadSound("assets/sounds/61847__simon-rue__boink-v3.wav"));
	int incrementX = 1;
	int incrementY = 1;

	public SilviaSprite() {
		super(50, 50, 0, 0, Loader.loadImage("assets/sprites/silvia.png"));
	}

	@Override
	public void move() {
		xPos+=xVelocity;
		yPos+=yVelocity;
		moveBouncing();

	}
	
	private void moveBouncing(){
		
		xPos += 1 * incrementX;
		yPos += 1 * incrementY;
		if (yPos < 1 || yPos >= 810) {

			incrementY *= (-1);
			boing.playSound();
		}
		if (xPos < 1 || xPos >= 1190) {
			incrementX *= (-1);
			boing.playSound();
		}
	}

}
