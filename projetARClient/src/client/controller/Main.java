package client.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import webservice.WebService;
import client.view.LoginFrame;
import client.view.PersonalPageFrame;
import client.view.RegisterFrame;
import client.view.TweetsView;

public class Main {
	
	private static TweetsView login;
	private static TweetsView register;
	private static TweetsView personalPage;
	
	private static ClientController controller;
	
	private static WebService webService = null;
	
	public static void init() throws MalformedURLException, RemoteException, NotBoundException{
		controller = new ClientController();
		login = new LoginFrame(controller);
		register = new RegisterFrame(controller);
		personalPage = new PersonalPageFrame(controller);
		controller.setLogin(login);
		controller.setRegister(register);
		controller.setPersonalPage(personalPage);
		
		Registry registry = LocateRegistry.getRegistry("192.168.1.87", 8000); 
		
		webService = (WebService) registry.lookup("rmi://192.168.1.87:8000/webService");
		controller.setWebservice(webService);
		
		controller.displayLogin();
	}
	
	public static void main(String[] agrs) throws MalformedURLException, RemoteException, NotBoundException{
		init();
	}

}
