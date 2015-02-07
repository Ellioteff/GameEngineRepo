package GameEngine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Renderer extends JFrame{
	/*Creates an instance of a Renderer that is a subclass of JFrame which will act as out Window for the game,
	 Makes the Renderer in the middle of the screen and sets some default values such as name and size of the Renderer. */
	public Renderer(String name, int width, int height){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (width / 2), 
		                              middle.y - (height / 2));
		
		setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());		
		setResizable(false);
		setLocation(newLocation);
		setPreferredSize(new Dimension(width, height));
		pack();
		setVisible(true);
		
	}

	
		
	
}
