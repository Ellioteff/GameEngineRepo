package GameEngine;

import java.io.Serializable;

//gameobject class that can contain a sprite, gameobjects does not necessarily have a sprite representation
//which is why we made this as a container for sprites.
public class GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	protected Sprite sprite;
	private boolean hasSprite = false;
	private int id;
	private static int idCounter = 0;

	//uses a static id variable to set the gameobjects id to a unique value in both constructors, makes for easier equals method.

	public GameObject() {
		id = idCounter;
		idCounter++;

	}
	public GameObject(Sprite s) {
		sprite = s;
		hasSprite = true;
		id = idCounter;
		idCounter++;
	}

	public void addSprite(Sprite s) {
		sprite = s;
		hasSprite = true;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean hasSprite() {
		return hasSprite;
	}

	public Sprite getSprite() {
		if (!hasSprite)
			return null;
		return sprite;

	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof GameObject) {
			GameObject go = (GameObject) other;
			return this.equals(go);

		}

		return false;
	}

	public boolean equals(GameObject other) {
		if (id == other.getId())
			return true;
		return false;

	}

	public String toString() {
		String s = "" + id;
		if (hasSprite)
			s += "\n" + sprite.toString();
		return s;
	}
}
