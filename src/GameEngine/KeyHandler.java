package GameEngine;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class KeyHandler {

	private static String text;
	private static KeyStroke keyStroke;

	public KeyHandler(String text, KeyStroke keyStroke) {

		KeyHandler.text = text;
		KeyHandler.keyStroke = keyStroke;
		ActionMap actionMap = Game.getFrame().getRootPane().getActionMap();
		InputMap inputMap = Game.getFrame().getRootPane()
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		bindKeys(actionMap, inputMap, Game.getFrame());
		addAction(text);

	}

	private static String addAction(String text) {
				
		return text;
	}

	public static void bindKeys(ActionMap actionMap, InputMap inputMap,
			JFrame frame) {

		actionMap.put(getText(), new KeyBinding(getText()));
		inputMap.put(getKeyStroke(), getText());

		frame.getRootPane().setActionMap(actionMap);
		frame.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW,
				inputMap);
	}

	public static String getText() {
		return text;
	}

	public static KeyStroke getKeyStroke() {
		return keyStroke;
	}

	private static class KeyBinding extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public KeyBinding(String text) {
			super(text);
			putValue(ACTION_COMMAND_KEY, text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String action = e.getActionCommand();
			//int str = (int) addAction(action);
			//System.out.println(action);
			
			switch (action) {

			case "Space":
				System.out.println("space");
				break;
			case "Up":
				System.out.println("up");
				break;
			case "Down":
				System.out.println("down");
				break;
			case "Left":
				System.out.println("left");
				break;
			case "Right":
				System.out.println("right");
				break;
			case "Escape":
				System.exit(0);
				break;
			default: 
				break;
			}
		}

	}

}
