package GameEngine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static BufferedImage loadImage(String filePath){
		BufferedImage spriteImage;
		try {
			spriteImage = ImageIO.read(new File(filePath));
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return spriteImage;
		
		
	}

}
