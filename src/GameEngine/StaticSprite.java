package GameEngine;

import java.awt.image.BufferedImage;

public class StaticSprite extends Sprite {
	
	//unused class as of right now but is planned to be used for static objects such as text for future implementations.

	protected StaticSprite(int width, int height, int x, int y, BufferedImage image) {
		super(width, height, x, y, image);
		
	}

	private static final long serialVersionUID = 1L;


}