package webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import client.controller.ClientAction;

public interface Service extends Remote{
	public boolean addFollowing(String topicName,ClientAction clientAction) throws RemoteException;
	
	public int getNbFollowing(String userName) throws RemoteException;
	
	public int getNbFollower(String userName) throws RemoteException;
	
	public boolean writeTweet(String userName,String topic, String message) throws RemoteException;
	
	public List<String> getAllUser() throws RemoteException;
	
	public List<String> getAllFollowing(String userName) throws RemoteException;
	
}
