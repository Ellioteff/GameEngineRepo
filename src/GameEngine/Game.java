package GameEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Serializable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Renderer render;
	private PlayerObject player;
	private boolean keepRunning;

	private double Fps = 60D;
	long currentTime = System.nanoTime();
	double nsPerTick = 1000000000D / Fps;
	long lastTimer = System.currentTimeMillis();
	double delta = 0;
	HashMap<Integer, Runnable> keysPressed = new HashMap<>();
	HashMap<Integer, Runnable> keysReleased = new HashMap<>();

	private ArrayList<DynamicSprite> dynamicSprites = new ArrayList<DynamicSprite>();
	private ArrayList<StaticSprite> staticSprites = new ArrayList<StaticSprite>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();


	

	public Game(String name, int width, int height, PlayerObject p) {
		this.player = p;	
		this.render = new Renderer(name, width, height);
		render.addKeyListener(this);		
		keepRunning = true;
		addGameObject(p);

	}


	public void bindKeyPressed(Integer e, KeyBinding keyBinding) {
		keysPressed.put(e, keyBinding);

	}

	public void bindKeyReleased(Integer e, KeyBinding keyBinding) {
		keysReleased.put(e, keyBinding);

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
		render.repaint();
	}

	public int getWidth() {
		return render.getWidth();
	}

	public int getHeight() {
		return render.getHeight();
	}

	public PlayerObject getPlayer() {
		return player;
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


	public void addGameObject(GameObject go) {
		gameObjects.add(go);

		if (go.hasSprite()) {
			if (go.getSprite() instanceof DynamicSprite)
				dynamicSprites.add((DynamicSprite) go.getSprite());
			else if (go.getSprite() instanceof StaticSprite)
				staticSprites.add((StaticSprite) go.getSprite());
			render.add(go.getSprite());
		}
		render.validate();

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyBinding = e.getKeyCode();

		for (Integer key : keysPressed.keySet()) {
			if (keyBinding == key)
				keysPressed.get(keyBinding).run();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyBinding = e.getKeyCode();

		for (Integer key : keysReleased.keySet()) {
			if (keyBinding == key)
				keysReleased.get(keyBinding).run();
		}
	}

}
