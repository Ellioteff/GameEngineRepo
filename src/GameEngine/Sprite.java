package GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JComponent;

public abstract class Sprite extends JComponent {
	private BufferedImage spriteImage;
	private Graphics2D spriteImageG2D;
	private Area area;
	private int xPos;
	private int yPos;

	public Sprite() {

	}

	public void actOnCollision(Sprite s) {
		System.out.println("collision with " + s.toString());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(spriteImage, 0, 0, this);

	}

	protected Sprite(int width, int height, int x, int y, BufferedImage image) {

		area = new Area(new Rectangle(width, height));
		xPos = x;
		yPos = y;
		spriteImage = image;
		spriteImageG2D = (Graphics2D) this.spriteImage.getGraphics();

	}

	public Graphics2D getSpriteG2D() {
		return spriteImageG2D;
	}

	public abstract void move();

}
