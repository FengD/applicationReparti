package client.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface ClientAction extends Remote{
	public String getPassword() throws RemoteException;

	public String getUserName() throws RemoteException;

	public void newTweets(HashMap<String, String> tweets) throws RemoteException;
	
	public void updateFollowings(int nb) throws RemoteException;

	public void updateFollowers(int nb) throws RemoteException;
	
	public void refreshListTout() throws RemoteException;

	public boolean isConnecte() throws RemoteException;

	public void refreshTopics() throws RemoteException;
	
	public void newUser(String userName) throws RemoteException;

}
