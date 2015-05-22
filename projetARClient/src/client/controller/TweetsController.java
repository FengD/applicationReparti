package client.controller;

import client.view.TweetsView;

public class TweetsController {
	private TweetsView login;
	private TweetsView register;
	private TweetsView personalPage;
	
	public TweetsController(){
		
	}
	
	public void setLogin(TweetsView login){
		this.login = login;
	}
	
	public void setRegister(TweetsView register){
		this.register = register;
	}
	
	public void setPersonalPage(TweetsView personalPage){
		this.personalPage = personalPage;
	}
	
	public void displayLogin(){
		login.display();
	}
	
	public void closeLogin(){
		login.close();
	}
	
	public void displayRegister(){
		register.display();
	}
	
	public void closeRegister(){
		register.close();
	}
	
	public void displayPersonalPage(){
		personalPage.display();
	}
	
	public void closePersonalPage(){
		personalPage.close();
	}

}
