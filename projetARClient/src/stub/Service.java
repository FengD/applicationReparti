package stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote{
	public boolean addFollowing(String topicName) throws RemoteException;
	
//	public boolean deleteFollowing(String topicName) throws RemoteException;
	
	public boolean writeTweet(String userName,String message) throws RemoteException;
	
//	public boolean deleteTweet() throws RemoteException;
//	public ArrayList<String> viewFollowings() throws RemoteException;
//	public String searchTopic() throws RemoteException;
//	public ArrayList<String> viewTopics() throws RemoteException;
}
