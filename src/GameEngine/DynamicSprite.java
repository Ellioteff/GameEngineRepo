package GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DynamicSprite extends Sprite {
	protected double yVelocity = 0;
	protected double xVelocity = 0;

	public DynamicSprite(int width, int height, int x, int y, BufferedImage image) {
		super(width,height,x,y,image);
		canCollide = true;
	}
	public void move() {
	}
	
	
	
	
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

}
