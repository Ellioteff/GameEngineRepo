package GameEngine;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private int width;
	private int height;
	
	private boolean keepRunning;
	private int tickCount;
	private int[] pixels;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	protected Game(String name, int width, int height) {

		keepRunning = true;
		

		frame = new JFrame(name);
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setPreferredSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		boolean render = true;
		
		
		
      

		while (keepRunning != false) {
			long present = System.nanoTime();
			delta += (present - lastTime) / nsPerTick;
			lastTime = present;
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				render = true;
			}
			try{
				Thread.sleep(2);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			if (render) {
				frames++;
				render();
			}
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println(ticks + "  " +frames);
				frames = 0;
				ticks = 0;
			}
			for(Sprite s : sprites)
				s.move();
			
		}
	}

	private void tick() {
		tickCount++;
		
	}

	public void render() {
		frame.repaint();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void addSprite(Sprite s){
		sprites.add(s);
		frame.add(s);
		frame.validate();
		
		
	}
}
