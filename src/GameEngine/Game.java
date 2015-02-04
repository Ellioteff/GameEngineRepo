package GameEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class Game implements Serializable, KeyListener {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private int width;
	private int height;
	private PlayerObject player;
	private boolean keepRunning;

	private double Fps = 60D;
	long currentTime = System.nanoTime();
	double nsPerTick = 1000000000D / Fps;
	long lastTimer = System.currentTimeMillis();
	double delta = 0;
	HashMap<Integer, Runnable> keys = new HashMap<>();

	private ArrayList<DynamicSprite> dynamicSprites = new ArrayList<DynamicSprite>();
	private ArrayList<StaticSprite> staticSprites = new ArrayList<StaticSprite>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public Game(String name, int width, int height, PlayerObject p) {
		this.player = p;
		frame = new JFrame(name);
		frame.setPreferredSize(new Dimension(width, height));
		setup();
		addGameObject(p);

	}

	public void bindKey(Integer e, KeyBinding keyBinding) {

		
		keys.put(e, () -> new KeyBinding());
		
		
	}

	public void run() {

		while (keepRunning != false) {
			long present = System.nanoTime();
			delta += (present - currentTime) / nsPerTick;
			currentTime = present;

			try {
				Thread.sleep(3);
				if (System.currentTimeMillis() - lastTimer >= 1.67) {
					lastTimer += 1.67;
					moveSprites();
					checkForCollisions();
					render();
				}
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		}

	}

	private void moveSprites() {
		for (DynamicSprite s : dynamicSprites)
			s.move();
	}

	public void setFps(double fps) {
		this.Fps = fps;
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
		frame.addKeyListener(this);
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

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyBinding = e.getKeyCode();
		
		
		if(keyBinding == KeyEvent.VK_LEFT){
			keys.get(keyBinding).run();
		}
		if(keyBinding == KeyEvent.VK_RIGHT){
			keys.get(keyBinding).run();
		}
		if(keyBinding == KeyEvent.VK_SPACE){
			keys.get(keyBinding).run();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
