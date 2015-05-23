package webservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
	public boolean addFollowing(String topicName, ClientAction clientAction) throws RemoteException {
		Topic topic = new Topic(topicName);
		userController.addFollowing(topic,clientAction);
		return false;
	}

	@Override
	public boolean writeTweet(String userName, String topic,String message)
			throws RemoteException {
		User owner = userController.findUserByName(userName);
		Pub.setupPublisher(owner,topic,message);
		return true;
	}

}
