package GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public abstract class Sprite extends JComponent {
	private static final long serialVersionUID = 1L;
	protected BufferedImage spriteImage;
	protected Rectangle spriteArea;
	protected double xPos;
	protected double yPos;
	protected int health = 100;

	// returns a copy of the rectangle that represents the spritearea; the area
	// in which the sprite exists.
	@Override
	public Rectangle getBounds() {
		Rectangle temp = new Rectangle(spriteArea);
		return temp;
	}

	public int getHealth() {
		return health;
	}
	//constructor that creates a spriteArea out of the variables sent in. Requires a bufferedimage which is why the loadImage method exists
	//in Loader.java
	protected Sprite(int width, int height, int x, int y, BufferedImage image) {
		spriteArea = new Rectangle(x, y, width, height);
		xPos = x;
		yPos = y;
		spriteImage = image;
	}

	// overrides jcomponents paintcomponent which allows for automatic redrawing
	// when the redraw method is called in the JFrame
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// sets the spriteareas x and y values to the same as xpos and ypos and
		// rounds them down to whole integers
		spriteArea.x = (int) Math.round(xPos);
		spriteArea.y = (int) Math.round(yPos);
		// draws the image on the JFrame which it has been added to.
		g.drawImage(spriteImage, spriteArea.x, spriteArea.y, (int) spriteArea.getWidth(), (int) spriteArea.getHeight(), this);

	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public String toString() {
		return "X-position: " + xPos + "\nY-Position: " + yPos + "\n";

	}

}
