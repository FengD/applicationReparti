package webservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controller.UserController;

public class WebServiceImpl extends UnicastRemoteObject implements WebService {
	
	private static final long serialVersionUID = 1L;
	private Service service;
	private UserController controller;
	
	public WebServiceImpl() throws RemoteException {
		super();
		service = new ServiceImpl();
		controller = new UserController();
	}

	
	@Override
	public boolean register(String name, String pwd) throws RemoteException {
		System.out.println("register name: "+name+" pwd: "+pwd);
		return controller.createNewUser(name, pwd);
	}

//	@Override
//	public boolean connect(String name, String pwd) throws RemoteException {
//		if (db.isExistUser(name)) {
//			if (db.getUserByName(name).checkPwd(pwd)) {
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public boolean disconnect() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
/**
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
**/


	@Override
	public Service login(String name, String pwd) throws RemoteException {
		System.out.println("login name: "+name+" pwd: "+pwd);
		if (controller.login(name, pwd)) {
			System.out.println("login OK");
			return service;
		}
		return null;
	}
}
