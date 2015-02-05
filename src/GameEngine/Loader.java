package GameEngine;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader {

	public static BufferedImage loadImage(String filePath) {
		BufferedImage spriteImage;
		try {
			spriteImage = ImageIO.read(new File(filePath));

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return spriteImage;

	}

	public static AudioInputStream loadSound(String filePath) {
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File(filePath));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		return audioIn;

	}

	public static void saveGame(Game g, String s) {
		try {
			OutputStream file = new FileOutputStream(s);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(g.getGameState());
			output.close();

		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}

	public static void loadGame(Game g, String s) {
		try {
			InputStream file = new FileInputStream(s);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			HashMap<Integer, Point> spritePositions= (HashMap<Integer, Point>) input.readObject();
			g.loadSpritePositions(spritePositions);
			input.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
