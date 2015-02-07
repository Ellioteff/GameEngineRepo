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
	long currentTime = System.nanoTime();
	long nsPerTick = (1000000000 / 300);
	long delta = 0;
	

	private HashMap<Integer, KeyBinding> keysPressed = new HashMap<>();
	private HashMap<Integer, KeyBinding> keysReleased = new HashMap<>();

	private ArrayList<DynamicSprite> dynamicSprites = new ArrayList<DynamicSprite>();
	private ArrayList<StaticSprite> staticSprites = new ArrayList<StaticSprite>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	/* Game constructor creates a new renderer that works as a window for the game and then adds a Keylistener 
	to that renderer and adds the player object to the renderer */
	public Game(String name, int width, int height, PlayerObject p) {
		this.player = p;
		this.render = new Renderer(name, width, height);
		render.addKeyListener(this);
		keepRunning = true;
		addGameObject(p);

	}
	/*Binds a key with a KeyBinding object (a Runnable Object) and puts it in a hashmap with all the other keys that are bound, 
	The keybindings are created in the application by using Javas 8s Lambdas*/
	public void bindKeyPressed(Integer e, KeyBinding keyBinding) {
		keysPressed.put(e, keyBinding);

	}

	public void bindKeyReleased(Integer e, KeyBinding keyBinding) {
		keysReleased.put(e, keyBinding);

	}

	//returns a hashmap containing sprite ids and their positions to be saved down on a file.
	public HashMap<Integer, Point> getGameState() {
		HashMap<Integer, Point> spritePositions = new HashMap<>();
		for (GameObject go : gameObjects) {
			if (go.hasSprite()) {
				Point p = new Point((int) go.getSprite().getxPos(), (int) go.getSprite().getyPos());
				spritePositions.put(go.getId(), p);

			}

		}
		return spritePositions;

	}
	//loads spritepositions from a hashmap that can be loaded from a file
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
	/* This method is the game loop which keeps the render updated with the sprite's movements and checks collisions with other objects */
	  
	public void run() {

		while (keepRunning != false) {

			long present = System.nanoTime();
			long updateLength = present - currentTime;
			currentTime = present;
			delta += updateLength;

			render();
			moveSprites();
			checkForCollisions();
			removeDeadObjects();

			try {
				Thread.sleep((currentTime - present + nsPerTick) / 1000000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

	}
	// for each DynamicSprite it calls its move function
	private void moveSprites() {
		for (DynamicSprite s : dynamicSprites)
			s.move();
	}
	/*creates a temporary arraylist of GameObjects and checks for gameobjects which has a sprite and has health equals or below 0,
	adds these to the list then if the list contains the player it shuts the renderer down then deletes the rest*/
	
	private void removeDeadObjects() {
		ArrayList<GameObject> temp = new ArrayList<>();
		for (GameObject go : gameObjects) {
			if (go.hasSprite() && go.getSprite().getHealth() <= 0) {
				temp.add(go);
			}
		}
		if (temp.contains(player)) {
			System.out.println("You lost");
			render.dispatchEvent(new WindowEvent(render, WindowEvent.WINDOW_CLOSING));

		}
		for (GameObject go : temp) {
			gameObjects.remove(go);
			dynamicSprites.remove(go.getSprite());
			staticSprites.remove(go.getSprite());
			render.remove(go.getSprite());
		}

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
	//looping through all DynamicSprites and calls for checkCollsion i the Physics class and if they do collide DynamicSprite function actOnCollsion is called
	private void checkForCollisions() {
		for (DynamicSprite s : dynamicSprites) {
			for (DynamicSprite s2 : dynamicSprites) {
				if (Physics.checkCollision(s, s2)) {
					s.actOnCollision(s2);
				}
			}
		}
	}
	//add the parametered GameObject to an arraylist, checks if it is an dynamic or static sprite then adds it to the renderer and validates
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
	/*Overrides KeyListeners methods, if the key that is pressed/release is equal to one that is in 
	the hashmap of keybindings that lambda expression is applied*/
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
