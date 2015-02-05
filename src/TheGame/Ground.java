package TheGame;

import java.awt.image.BufferedImage;

import GameEngine.DynamicSprite;
import GameEngine.Loader;

public class Ground extends DynamicSprite{

	private static final long serialVersionUID = 1L;

	public Ground(int x, int y) {
		super(1280, 100, x, y, Loader.loadImage("assets/sprites/Ground.png"));
		isSolid = true;
	}

}
