package GameEngine;
import java.awt.geom.Area;
import java.util.*;

public abstract class Sprite {
	Area area;
	
	public Sprite(){
		
	}
	
	public void actOnCollision(Sprite s){
		System.out.println("collision with "+s.toString());
	}
	
	public abstract void move();
	
}

