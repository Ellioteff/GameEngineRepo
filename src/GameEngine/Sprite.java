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

	@Override
	public Rectangle getBounds() {
		Rectangle temp = new Rectangle(spriteArea);
		return temp;
	}

	public int getHealth() {
		return health;
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


	protected Sprite(int width, int height, int x, int y, BufferedImage image) {
		spriteArea = new Rectangle(x, y, width, height);
		xPos = x;
		yPos = y;
		spriteImage = image;
	}
	
	public String toString(){
		return "X-position: "+xPos+"\nY-Position: "+yPos+"\n";
		
	}


	

}
