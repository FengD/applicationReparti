package webservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

	private static final long serialVersionUID = 1L;

	protected ServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addFollowing(String topicName) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeTweet(String userName, String message)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
