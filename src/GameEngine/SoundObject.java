package GameEngine;

import java.io.IOException;
import java.io.Serializable;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

public class SoundObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private DataLine.Info info;
	private AudioFormat af;
	private byte[] audio;
	private int size;

	public SoundObject(AudioInputStream audioIn) {

		try {
			af = audioIn.getFormat();
			size = (int) (af.getFrameSize() * audioIn.getFrameLength());
			audio = new byte[size];
			info = new DataLine.Info(Clip.class, af, size);
			audioIn.read(audio, 0, size);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playSound() {
		try {
			Clip localSound = (Clip) AudioSystem.getLine(info);
			localSound.open(af, audio, 0, size);
			localSound.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

}
