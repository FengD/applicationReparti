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
	List<Sub> subList;
	Pub pub;
	Database db;
	boolean isLogin;
	
	public UserController() {
		db = Database.getInstance();
		subList = new ArrayList<>();
		isLogin = false;
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
				isLogin = true;
				setupSubs(clientAction);
				return true;
			}
		}
		return false;
	}
	
	public void setupSubs(ClientAction clientAction){
		if (isLogin) {
			List<Topic> topics = user.getAllFollowing();
			if (topics != null && !topics.isEmpty()) {
				for (Topic topic : topics) {
					subList.add(new Sub(user.getName(), topic.getTopicName(),clientAction));
				}
			}
		}
	}
	
	public void addFollowing(Topic topic,ClientAction clientAction){
		if (isLogin) {
			user.addFollowing(topic);
			subList.add(new Sub(user.getName(), topic.getTopicName(),clientAction));
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
