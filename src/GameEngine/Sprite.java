package GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import java.util.*;

import javax.swing.JComponent;

public abstract class Sprite extends JComponent {
	private static final long serialVersionUID = 1L;
	protected BufferedImage spriteImage;
	private Graphics2D spriteImageG2D;
	protected Rectangle spriteArea;
	protected double xPos;
	protected double yPos;
	protected ArrayList<Rectangle> hitboxes;

	public Sprite() {

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

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;

		return true;
	}

}
