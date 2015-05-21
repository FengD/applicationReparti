package webservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

<<<<<<< HEAD
public class WebServiceImpl extends UnicastRemoteObject implements WebService {
=======
import db.Database;
import db.User;
>>>>>>> 8fd9dda931da77e7a2f725957ba816383922170a

public class WebServiceImpl extends UnicastRemoteObject implements WebService {
	
	private static final long serialVersionUID = 1L;
	Database db;
	

	public WebServiceImpl() throws RemoteException {
		super();
		db = Database.getInstance();
	}

	
	@Override
	public boolean register(String name, String pwd) throws RemoteException {
		if (db.isExistUser(name)) {
			return false;
		}
		db.signUpUser(new User(name, pwd));
		return true;
	}

	@Override
	public boolean connect(String name, String pwd) throws RemoteException {
		if (db.isExistUser(name)) {
			if (db.getUserByName(name).checkPwd(pwd)) {
				return true;
			}
		}
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
