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
	protected Rectangle spriteArea;
	protected double xPos;
	protected double yPos;

	public Sprite() {

	}

	public void actOnCollision(Sprite s) {
		System.out.println("collision with " + s.toString());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (int) Math.round(xPos);
		int y = (int) Math.round(yPos);
		g.drawImage(spriteImage, x, y, (int) spriteArea.getWidth(), (int) spriteArea.getHeight(), this);

	}

	protected Sprite(int width, int height, int x, int y, BufferedImage image) {

		spriteArea = new Rectangle(width, height);
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
