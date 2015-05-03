package webservice;

import java.rmi.RemoteException;
<<<<<<< HEAD
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class WebServiceImpl extends UnicastRemoteObject implements WebService{

	protected WebServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
=======
import java.util.ArrayList;

public class WebServiceImpl implements WebService {
>>>>>>> f96bf74f169c032fba28c3f3d2ea83db96729af7

	@Override
	public boolean register() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean connect() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disconnect() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFollowing() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFollowing() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeTweet() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTweet() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> viewFollowings() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchTopic() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> viewTopics() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
