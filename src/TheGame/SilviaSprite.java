package TheGame;

import GameEngine.DynamicSprite;
import GameEngine.SoundObject;

public class SilviaSprite extends DynamicSprite {
	SoundObject sound = new SoundObject("assets/sounds/Transformer Stingers 01 A.wav");
	int soundCounter = 0;

	public SilviaSprite() {
		super(200, 200, 200, 200, "assets/sprites/Silvia.png");
	}

	@Override
	public void move() {
		xPos+=1;
		soundCounter++;
		if(soundCounter == 200 || soundCounter == 500)
			sound.playSound();
		

	}

}
