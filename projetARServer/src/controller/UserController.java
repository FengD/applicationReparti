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
		if (user != null && !user.isLogin()) {
			if (user.checkPwd(pwd)) {
				user.setIsLogin(true);
				setupSubs(clientAction);
				return true;
			}
		}
		return false;
	}
	
	public boolean deconnecter(String name){
		System.out.println(name+" deconnecte");
		user = findUserByName(name);
		if (user != null && user.isLogin()) {
			user.setIsLogin(false);
			int i = 1;
			for (Sub sub : user.getSubs()) {
				System.out.println("deconnecte sub "+i);
				sub.close();
			}
			System.out.println("deconnecte ok");
			return true;
		}
		System.out.println("Cannot find user");
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
					user.addSub(new Sub(user.getName()+topic.getTopicName(), topic.getTopicName(),clientAction));
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
			addFollower(topic, user);
		}
		System.out.println("end add following");
	}
	
	private void addFollower(Topic topic, User user){
		User topicUser = findUserByName(topic.getTopicName());
		if (topicUser!=null) {
			topicUser.addFollowers(user);
		}
	}
	
	public User findUserByName(String userName){
		if (db.isExistUser(userName)) {
			return db.getUserByName(userName);
		}
		return null;
	}
	
	public int getNbFollowing(String userName){
		user = findUserByName(userName);
		List<Topic> followings = user.getAllFollowing();
		if (followings != null) {
			return followings.size();
		}
		return 0;
	}
	
	public int getNbFollower(String userName){
		user = findUserByName(userName);
		List<User> followers = user.getAllFollowers();
		if (followers != null) {
			return followers.size();
		}
		return 0;
	}
	
	public List<String> getAllUserName(){
		return db.getAllUserName();
	}
	
	
//	public static void main(String[] args) {
//		UserController userController = new UserController();
//		userController.createNewUser("shi", "shi");
//		userController.login("shi", "shi");
//		userController.addFollowing(new Topic("polytechMac"));
//	}
}
