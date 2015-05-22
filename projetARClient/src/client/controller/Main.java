package client.controller;

import client.view.LoginFrame;
import client.view.PersonalPageFrame;
import client.view.RegisterFrame;
import client.view.TweetsView;

public class Main {
	
	private static TweetsView login;
	private static TweetsView register;
	private static TweetsView personalPage;
	
	private static TweetsController controller;
	
	public static void init(){
		controller = new TweetsController();
		login = new LoginFrame(controller);
		register = new RegisterFrame(controller);
		personalPage = new PersonalPageFrame(controller);
		controller.setLogin(login);
		controller.setRegister(register);
		controller.setPersonalPage(personalPage);
		controller.displayLogin();
	}
	
	public static void main(String[] agrs){
		init();
	}

}
