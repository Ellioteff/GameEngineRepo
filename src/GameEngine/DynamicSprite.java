package GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class DynamicSprite extends Sprite {

	public DynamicSprite(int width, int height, int x, int y, BufferedImage image) {

		super(width,height,x,y,image);

	}
	public void move() {
		
	}
	
	protected void paintComponent(Graphics g) {
		System.out.println("ritar dynsprite");
		super.paintComponent(g);
		

	}

}
