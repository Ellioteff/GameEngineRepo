package GameEngine;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private int width;
	private int height;

	private boolean keepRunning;
	private int tickCount;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public Game(String name, int width, int height) {

		keepRunning = true;

		frame = new JFrame(name);
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setPreferredSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (keepRunning != false) {
			long present = System.nanoTime();
			delta += (present - lastTime) / nsPerTick;
			lastTime = present;
			while (delta >= 1) {
				delta -= 1;
				render();
			}
			try {
				Thread.sleep(2);
				if (System.currentTimeMillis() - lastTimer >= 1000) {
					lastTimer += 1000;
				}
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			
			for (Sprite s : sprites)
				s.move();

		}
	}

	public void render() {
		frame.repaint();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void addGameObject(GameObject go) {
		gameObjects.add(go);
		
		if(go.hasSprite())
		{
			sprites.add(go.getSprite());
		}
		frame.add(go.getSprite());
		frame.validate();

	}
}
