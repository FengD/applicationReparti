package webservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Service {
	public boolean addFollowing(String topicName) throws RemoteException;
	
//	public boolean deleteFollowing(String topicName) throws RemoteException;
	
	public boolean writeTweet(String userName,String message) throws RemoteException;
	
//	public boolean deleteTweet() throws RemoteException;
//	public ArrayList<String> viewFollowings() throws RemoteException;
//	public String searchTopic() throws RemoteException;
//	public ArrayList<String> viewTopics() throws RemoteException;
}
