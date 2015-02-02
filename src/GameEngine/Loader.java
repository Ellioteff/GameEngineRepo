package GameEngine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	
	public static AudioInputStream loadSound(String filePath){
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File (filePath));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		return audioIn;

	}

}
