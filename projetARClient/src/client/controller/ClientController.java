package client.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import webservice.Service;
import webservice.WebService;
import client.view.TweetsView;

public class ClientController extends UnicastRemoteObject implements ClientAction{
	protected ClientController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private TweetsView login;
	private TweetsView register;
	private TweetsView personalPage;
	
	private WebService webService;
	
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
	
	public void setWebservice(WebService webService){
		this.webService = webService;
	}
	
	public boolean register(String userName, String password) throws RemoteException{
		return webService.register(userName, password);
	}
	
	public Service login(String userName, String password) throws RemoteException{
		return webService.login(userName, password);
	}

	@Override
	public String getPassword() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshList() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshListTout() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnecte() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refreshTopics() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
