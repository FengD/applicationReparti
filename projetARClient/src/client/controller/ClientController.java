package client.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import webservice.Service;
import webservice.WebService;
import client.view.PersonalPageView;
import client.view.TweetsView;

public class ClientController extends UnicastRemoteObject implements ClientAction{
	protected ClientController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private TweetsView login;
	private TweetsView register;
	private PersonalPageView personalPage;
	
	private String userName;
	private String passWord;
	
	private WebService webService;
	
	private Service service;
	
	public void setService(Service service){
		this.service = service;
	}
	
	public Service getService(){
		return this.service;
	}
	
	public void setLogin(TweetsView login){
		this.login = login;
	}
	
	public void setRegister(TweetsView register){
		this.register = register;
	}
	
	public void setPersonalPage(PersonalPageView personalPage){
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
	
	public Service login(ClientAction clientAction) throws RemoteException{
		return webService.login(clientAction);
	}

	@Override
	public String getPassword() throws RemoteException {
		// TODO Auto-generated method stub
		return this.passWord;
	}

	@Override
	public String getUserName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public void newTweets(HashMap<String, String> tweets) throws RemoteException {
//		personalPage.setNews(tweets.get("message"));
		System.out.println(tweets.get("message"));
		
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
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.passWord = password;
	}

}
