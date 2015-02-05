package GameEngine;

public class KeyBinding implements Runnable {

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
