package GameEngine;

import java.io.Serializable;

public class GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	protected Sprite sprite;
	private boolean hasSprite = false;
	private int id;
	private static int idCounter = 0;

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
		return false;
	}
	
	public boolean equals(GameObject other){
		if(id == other.getId())
			return true;
		return false;
		
	}
	public String toString(){
		return ""+id;
	}
}
