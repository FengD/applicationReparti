package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import client.controller.ClientAction;
import db.Database;
import db.Topic;
import db.User;

public class UserController {
	User user;
	Pub pub;
	Database db;
	
	public UserController() {
		db = Database.getInstance();
	}
	
	public boolean createNewUser(String userName, String pwd){
		user = findUserByName(userName);
		if (user != null) {
			return false;
		}else {
			user = new User(userName, pwd);
			db.signUpUser(user);
			return true;
		}
	}
	
	public boolean login(ClientAction clientAction) throws RemoteException{
		String userName = clientAction.getUserName();
		String pwd = clientAction.getPassword();
		user = findUserByName(userName);
		if (user != null) {
			if (user.checkPwd(pwd)) {
				user.setIsLogin(true);
				setupSubs(clientAction);
				return true;
			}
		}
		return false;
	}
	
	public boolean deconnecter(String name){
		user = findUserByName(name);
		if (user != null) {
			user.setIsLogin(false);
			for (Sub sub : user.getSubs()) {
				sub.close();
			}
			return true;
		}
		return false;
	}
	
	public void setupSubs(ClientAction clientAction){
		try {
			user = findUserByName(clientAction.getUserName());
		} catch (RemoteException e) {
			user = null;
			e.printStackTrace();
		}
		if (user != null && user.isLogin()) {
			List<Topic> topics = user.getAllFollowing();
			if (topics != null && !topics.isEmpty()) {
				for (Topic topic : topics) {
					user.addSub(new Sub(user.getName(), topic.getTopicName(),clientAction));
				}
			}
		}
	}
	
	public void addFollowing(Topic topic,ClientAction clientAction){
		try {
			user = findUserByName(clientAction.getUserName());
		} catch (RemoteException e) {
			user = null;
			e.printStackTrace();
		}
		if (user != null && user.isLogin()) {
			user.addFollowing(topic);
			user.addSub(new Sub(user.getName()+topic.getTopicName(), topic.getTopicName(),clientAction));
		}
		System.out.println("end add following");
	}
	
	public User findUserByName(String userName){
		if (db.isExistUser(userName)) {
			return db.getUserByName(userName);
		}
		return null;
	}
	
	
//	public static void main(String[] args) {
//		UserController userController = new UserController();
//		userController.createNewUser("shi", "shi");
//		userController.login("shi", "shi");
//		userController.addFollowing(new Topic("polytechMac"));
//	}
}
