package GameEngine;

public class GameObject {
	private String name;
	protected Sprite sprite;
	private boolean hasSprite = false;

	public GameObject() {

	}
	public GameObject(Sprite s) {
		sprite = s;
		hasSprite = true;
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
}
