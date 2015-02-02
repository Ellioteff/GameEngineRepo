package GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public abstract class Sprite extends JComponent {
	private BufferedImage spriteImage;
	private Graphics2D spriteImageG2D;
	protected boolean canCollide = false;
	protected Rectangle spriteArea;
	protected double xPos;
	protected double yPos;
	protected boolean hasHitbox = false;
	protected ArrayList<Rectangle> hitboxes;

	public Sprite() {

	}

	public void actOnCollision(Sprite s) {
		System.out.println("collision with " + s.toString());
	}

	public boolean canCollide() {
		return canCollide;
	}

	public boolean hasHitbox() {
		return hasHitbox;
	}

	public void addHitbox(int x, int y, int width, int height) {
		if (hitboxes == null)
			hitboxes = new ArrayList<Rectangle>();
		hitboxes.add(new Rectangle(x, y, width, height));
		hasHitbox = true;
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
	public Rectangle getBounds() {
		Rectangle temp = new Rectangle(spriteArea);
		return temp;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		spriteArea.x = (int) Math.round(xPos);
		spriteArea.y = (int) Math.round(yPos);
		int x = (int) Math.round(xPos);
		int y = (int) Math.round(yPos);
		g.drawImage(spriteImage, x, y, (int) spriteArea.getWidth(), (int) spriteArea.getHeight(), this);
		if (hasHitbox) {
			for (Rectangle r : hitboxes)
				g.fillRect(((int) (r.x + xPos)), ((int) (r.y + yPos)), r.width, r.height);
		}

	}

	protected Sprite(int width, int height, int x, int y, BufferedImage image) {
		spriteArea = new Rectangle(x, y, width, height);
		xPos = x;
		yPos = y;
		spriteImage = image;
		spriteImageG2D = (Graphics2D) this.spriteImage.getGraphics();

	}

	public Graphics2D getSpriteG2D() {
		return spriteImageG2D;
	}

	public abstract void move();

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;

		return true;
	}

}
