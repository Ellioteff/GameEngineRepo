package GameEngine;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public enum Keys {

			ESCAPE("Escape", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)), 
			UP("Up",KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)), 
			DOWN("Down", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0)),
			LEFT("Left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0)), 
			RIGHT("Right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)),
			SPACE("Space", KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));

	private String text;
	private KeyStroke keyStroke;

	Keys(String text, KeyStroke keyStroke) {
		this.text = text;
		this.keyStroke = keyStroke;
	}

	public String getText() {
		return text;
	}

	public KeyStroke getKeyStroke() {
		return keyStroke;
	}
}
