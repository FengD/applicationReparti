package webservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import client.controller.ClientAction;
import controller.Pub;
import controller.UserController;
import db.Topic;
import db.User;

public class ServiceImpl extends UnicastRemoteObject implements Service {

	private static final long serialVersionUID = 1L;
	private UserController userController;

	protected ServiceImpl(UserController userController) throws RemoteException {
		super();
		this.userController = userController;
	}

	@Override
	public boolean addFollowing(String topicName, ClientAction clientAction)
			throws RemoteException {
		Topic topic = new Topic(topicName);
		userController.addFollowing(topic, clientAction);
		return false;
	}

	@Override
	public boolean writeTweet(String userName, String topic, String message)
			throws RemoteException {
		System.out.println("start write tweet");
		User owner = userController.findUserByName(userName);
		System.out.println("owner " + owner.getName());
		System.out.println("topic " + topic);
		System.out.println("message " + message);
		userController.publish(owner, topic, message, true);
		return true;
	}

	@Override
	public int getNbFollowing(String userName) throws RemoteException {
		return userController.getNbFollowing(userName);
	}

	@Override
	public List<String> getAllUser() throws RemoteException {
		return userController.getAllUserName();
	}

	@Override
	public int getNbFollower(String userName) throws RemoteException {
		return userController.getNbFollower(userName);
	}

}
