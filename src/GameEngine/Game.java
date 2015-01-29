package GameEngine;

import java.awt.*;
import javax.swing.*;

public class Game extends Canvas{
	
	private JFrame frame;
	private int width;
	private int height;

	protected Game(String name, int width, int height){
		
		frame = new JFrame(name);
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
