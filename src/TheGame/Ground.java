package TheGame;

import java.awt.image.BufferedImage;

import GameEngine.DynamicSprite;
import GameEngine.Loader;

public class Ground extends DynamicSprite{

	private static final long serialVersionUID = 1L;

	public Ground() {
		super(1280, 100, 0, 780, Loader.loadImage("assets/sprites/Ground.png"));
		isSolid = true;
	}

}
