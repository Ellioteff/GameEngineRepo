package GameEngine;

import java.io.Serializable;

public class KeyBinding implements Runnable,Serializable {
	private static final long serialVersionUID = 1L;
	Runnable run;

	KeyBinding() {

	}

	public KeyBinding(Runnable run) {
		this.run = run;
	}

	@Override
	public void run() {
		run.run();
	}

}
