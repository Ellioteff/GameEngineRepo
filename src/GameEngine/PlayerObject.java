package GameEngine;

public class PlayerObject extends GameObject {
	
	//subclass to gameobject in order to force the game to always have a playerobject in the gameobject.
	public PlayerObject(PlayerSprite ps){
		super(ps);
	}

}
