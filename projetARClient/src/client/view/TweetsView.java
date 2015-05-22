package client.view;

import client.controller.TweetsController;


public abstract class TweetsView implements TweetsChangedListener{
	private TweetsController tweetsController = null;

	public final TweetsController getController() {
		return tweetsController;
	}

	public void setController(TweetsController t) {
		tweetsController = t;
	}

	public abstract void display();

	public abstract void close();

}
