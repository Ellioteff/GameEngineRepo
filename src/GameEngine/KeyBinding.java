package GameEngine;

import java.io.Serializable;

public class KeyBinding implements Runnable,Serializable {
	private static final long serialVersionUID = 1L;
	Runnable run;

	KeyBinding() {

	}
	//Takes parameter of a lambda exprssion and sets it to KeyBindings Runnable variable which is the method that can be called under this constructor.
	public KeyBinding(Runnable run) {
		this.run = run;
	}
	
	@Override
	public void run() {
		run.run();
	}

}
