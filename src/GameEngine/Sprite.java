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
	private Area area;
	protected int xPos;
	protected int yPos;

	public Sprite() {

	}

	public void actOnCollision(Sprite s) {
		System.out.println("collision with " + s.toString());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(spriteImage, xPos, yPos, this);

	}

	protected Sprite(int width, int height, int x, int y, String imagePath) {
		
		System.out.println("sprite konstruktor");
		area = new Area(new Rectangle(width, height));
		xPos = x;
		yPos = y;
		addImage(imagePath);

	}

	public Graphics2D getSpriteG2D() {
		return spriteImageG2D;
	}
	
	private void addImage(String imagePath){
		try {
			spriteImage = ImageIO.read(new File(imagePath));
			spriteImageG2D = (Graphics2D) this.spriteImage.getGraphics();
			
		} catch (IOException e) {
			System.out.println("couldnt open/find file");
		}
	}

	public abstract void move();

}
