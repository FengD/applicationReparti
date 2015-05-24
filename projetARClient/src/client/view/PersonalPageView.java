package client.view;

import client.controller.ClientController;


public abstract class PersonalPageView {
	private ClientController tweetsController = null;

	public final ClientController getController() {
		return tweetsController;
	}

	public void setController(ClientController t) {
		tweetsController = t;
	}

	public abstract void display();

	public abstract void close();
	
	public abstract void setNews(String news);
	
	public abstract void setUserInfo(String name);
	
	public abstract void setFollowings(String nbFollowings);
	
	public abstract void setFollowers(String nbFollowers);
	
	public abstract void setUsersBox(Object[] usersName);
	
	public abstract void addNewUser(String newUser);

}
