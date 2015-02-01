package GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class DynamicSprite extends Sprite {

	public DynamicSprite(int width, int height, int x, int y, String imagePath) {
		super(width,height,x,y,imagePath);

	}
	public void move() {
		xPos+=1;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		

	}

}
