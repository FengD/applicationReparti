package webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface WebService extends Remote{
	public boolean register() throws RemoteException;
	public boolean connect() throws RemoteException;
	public boolean disconnect() throws RemoteException;
	public boolean addFollowing() throws RemoteException;
	public boolean deleteFollowing() throws RemoteException;
	public boolean writeTweet() throws RemoteException;
	public boolean deleteTweet() throws RemoteException;
	public ArrayList<String> viewFollowings() throws RemoteException;
	public String searchTopic() throws RemoteException;
	public ArrayList<String> viewTopics() throws RemoteException;
}