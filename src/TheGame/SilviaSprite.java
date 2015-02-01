package TheGame;

import GameEngine.DynamicSprite;
import GameEngine.SoundObject;

public class SilviaSprite extends DynamicSprite {
	SoundObject sound = new SoundObject("assets/sounds/Transformer Stingers 01 A.wav");
	SoundObject boing = new SoundObject("assets/sounds/61847__simon-rue__boink-v3.wav");
	int soundCounter = 0;
	int incrementX = 1;
	int incrementY = 1;

	public SilviaSprite() {
		super(50, 50, 0, 0, "assets/sprites/Silvia.png");
	}

	@Override
	public void move() {
		xPos += 1 * incrementX;
		yPos += 1 * incrementY;
		if (yPos < 1 || yPos >= 829) {

			incrementY *= (-1);
			boing.playSound();
		}
		if (xPos < 1 || xPos >= 1190) {
			incrementX *= (-1);
			boing.playSound();
		}
		/*soundCounter++;
		if (soundCounter == 1)
			sound.playSound();*/

	}

}
