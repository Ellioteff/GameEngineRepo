package GameEngine;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DynamicSprite extends Sprite {
	private static final long serialVersionUID = 1L;
	protected double yVelocity = 0;
	protected double xVelocity = 0;
	protected double previousX = 0;
	protected double previousY = 0;
	protected boolean isSolid = false;
	protected boolean onGround = false;
	protected boolean hasHitbox = false;
	protected ArrayList<Rectangle> hitboxes;


	public DynamicSprite(int width, int height, int x, int y, BufferedImage image) {
		super(width, height, x, y, image);
	}

	public boolean hasHitbox() {
		return hasHitbox;
	}

	public void actOnCollision(DynamicSprite s) {
	}

	protected void stepBackFromCollision() {
		yPos = (long)previousY-1;
		yVelocity = 0;
		onGround = true;
	}

	public ArrayList<Rectangle> getHitbox() {
		ArrayList<Rectangle> temp = new ArrayList<Rectangle>();
		int x = (int) Math.round(xPos);
		int y = (int) Math.round(yPos);
		for (Rectangle rect : hitboxes) {
			Rectangle r = new Rectangle(rect.x + x, rect.y + y, rect.width, rect.height);
			temp.add(r);
		}

		return temp;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
// the code below can be used to display the hitbox		
//		if (hasHitbox) {
//			for (Rectangle r : hitboxes)
//				g.fillRect(((int) (r.x + xPos)), ((int) (r.y + yPos)), r.width, r.height);
//		}

	}

	public void addHitbox(int x, int y, int width, int height) {
		if (hitboxes == null)
			hitboxes = new ArrayList<Rectangle>();
		hitboxes.add(new Rectangle(x, y, width, height));
		hasHitbox = true;
	}

	public boolean onGround() {
		return onGround;
	}

	public boolean isSolid() {
		return isSolid;
	}
	
	public void setPrevious(){
		previousX = xPos;
		previousY = yPos;
	}
		
	
	public void move() {
		this.setPrevious();
		xPos += xVelocity;
		yPos += yVelocity;
	}

}
