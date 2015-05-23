package client.view;

import client.controller.ClientController;


public abstract class TweetsView {
	private ClientController tweetsController = null;

	public final ClientController getController() {
		return tweetsController;
	}

	public void setController(ClientController t) {
		tweetsController = t;
	}

	public abstract void display();

	public abstract void close();

}
