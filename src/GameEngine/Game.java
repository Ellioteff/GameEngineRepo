package GameEngine;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private static JFrame frame;
	private int width;
	private int height;

	private boolean keepRunning;
	private double Fps = 60D;
	long currentTime = System.nanoTime();
	double nsPerTick = 1000000000D / Fps;
	long lastTimer = System.currentTimeMillis();
	double delta = 0;
	private ArrayList<DynamicSprite> dynamicSprites = new ArrayList<DynamicSprite>();
	private ArrayList<StaticSprite> staticSprites = new ArrayList<StaticSprite>();

	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public Game(String name, int width, int height) {

		frame = new JFrame(name);
		frame.setPreferredSize(new Dimension(width, height));
		setup();

	}

	public void run() {

		while (keepRunning != false) {
			long present = System.nanoTime();
			delta += (present - currentTime) / nsPerTick;
			currentTime = present;
			while (delta >= 1) {

				delta -= 1;
				// render();

			}

			try {
				Thread.sleep(3);
				if (System.currentTimeMillis() - lastTimer >= 1000) {
					lastTimer += 1000;
				}
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			moveSprites();
			checkForCollisions();
			render();
		}

	}

	private void moveSprites() {
		for (DynamicSprite s : dynamicSprites)
			s.move();
	}

	public double getFps() {
		return Fps;
	}

	public void setFps(double fps) {
		this.Fps = fps;
	}

	public void render() {
		frame.repaint();
	}

	public static JFrame getFrame() {
		return frame;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void checkForCollisions() {
		for (DynamicSprite s : dynamicSprites) {
			for (DynamicSprite s2 : dynamicSprites) {
				if (Physics.checkCollision(s, s2)) {
					s.actOnCollision(s2);
				}
			}
		}
	}

	public void setup() {
		keepRunning = true;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void addGameObject(GameObject go) {
		gameObjects.add(go);

		if (go.hasSprite()) {
			if (go.getSprite() instanceof DynamicSprite)
				dynamicSprites.add((DynamicSprite) go.getSprite());
			else if (go.getSprite() instanceof StaticSprite)
				staticSprites.add((StaticSprite) go.getSprite());
			frame.add(go.getSprite());
		}
		frame.validate();

	}

}
