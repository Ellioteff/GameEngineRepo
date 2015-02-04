package GameEngine;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import GameEngine.Keys;


public class keyHandler {

	static void bindKey(JFrame frame){
		
		ActionMap actionMap = frame.getRootPane().getActionMap();
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        for (Keys direction : Keys.values())
        {
            actionMap.put(direction.getText(), new KeyBinding(direction.getText()));
            inputMap.put(direction.getKeyStroke(), direction.getText());
        }
        frame.getRootPane().setActionMap(actionMap);
        frame.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
	}
	private static class KeyBinding extends AbstractAction {

        private static final long serialVersionUID = 1L;

        public KeyBinding(String text){
            super(text);
            putValue(ACTION_COMMAND_KEY, text);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
        	String action = e.getActionCommand();
            switch(action){
            
            case "Space": 
            	System.out.println("Space");
            	break;
            case "Up": 
            	
            	break;
            case "Down": 
            	
            	break;
            case "Left": 
            	
            	break;
            case "Right": 
            	
            	break;
            	
            }
        }

    }
}
