package webservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.ClientAction;
import controller.UserController;

public class WebServiceImpl extends UnicastRemoteObject implements WebService {
	
	private static final long serialVersionUID = 1L;
	private Service service;
	private UserController userController;
	
	public WebServiceImpl() throws RemoteException {
		super();
		userController = new UserController();
	}

	
	@Override
	public boolean register(String name, String pwd) throws RemoteException {
		System.out.println("register name: "+name+" pwd: "+pwd);
		return userController.createNewUser(name, pwd);
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
	public Service login(ClientAction clientAction) throws RemoteException {
		String name = clientAction.getUserName();
		String pwd = clientAction.getPassword();
		System.out.println("login name: "+name+" pwd: "+ pwd);
		if (userController.login(clientAction)) {
			System.out.println("login OK");
			service = new ServiceImpl(userController);
			return service;
		}
		return null;
	}
}
