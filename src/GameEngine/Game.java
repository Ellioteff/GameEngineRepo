package GameEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Serializable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Renderer render;
	private PlayerObject player;
	private boolean keepRunning;

	private int Fps = 60;
	long currentTime = System.nanoTime();
	long nsPerTick = (1000000000 / Fps);

	private HashMap<Integer, KeyBinding> keysPressed = new HashMap<>();
	private HashMap<Integer, KeyBinding> keysReleased = new HashMap<>();

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

	public HashMap<Integer, Point> getGameState() {
		HashMap<Integer, Point> spritePositions = new HashMap<>();
		for (GameObject go : gameObjects) {
			if (go.hasSprite()) {
				Point p = new Point((int) go.getSprite().getxPos(), (int) go
						.getSprite().getyPos());
				System.out.println(p);
				spritePositions.put(go.getId(), p);

			}

		}
		return spritePositions;

	}

	public void loadSpritePositions(HashMap<Integer, Point> spritePositions) {
		for (GameObject go : gameObjects) {
			if (go.hasSprite()) {
				go.getSprite().setxPos(spritePositions.get(go.getId()).getX());
				go.getSprite().setyPos(spritePositions.get(go.getId()).getY());
				if (go.getSprite() instanceof DynamicSprite) {

					DynamicSprite s = (DynamicSprite) go.getSprite();
					s.onGround = false;
				}

			}

		}

	}

	public void run() {

		while (keepRunning != false) {

			long present = System.nanoTime();
			long updateLength = present - currentTime;
			currentTime = present;
			double delta = updateLength / ((double) nsPerTick);
System.out.println(delta);
			moveSprites(delta);
			checkForCollisions();
			removeDeadObjects();
			render();

			try {
				Thread.sleep((currentTime - present + nsPerTick) / 1000000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

	}

	private void moveSprites(double delta) {
		for (DynamicSprite s : dynamicSprites)
			s.move(delta);
	}

	private void removeDeadObjects() {
		ArrayList<GameObject> temp = new ArrayList<>();
		for (GameObject go : gameObjects) {
			if (go.hasSprite() && go.getSprite().getHealth() <= 0) {
				temp.add(go);
			}
		}
		if (temp.contains(player)) {
			System.out.println("You lost");
			render.dispatchEvent(new WindowEvent(render,
					WindowEvent.WINDOW_CLOSING));

		}
		for (GameObject go : temp) {
			gameObjects.remove(go);
			dynamicSprites.remove(go.getSprite());
			staticSprites.remove(go.getSprite());
			render.remove(go.getSprite());
		}

	}

	public void setFps(int fps) {
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

	public String toString() {
		return gameObjects.toString();
	}

}
