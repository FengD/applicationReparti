package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.controller.ClientAction;
import db.Database;
import db.Topic;
import db.User;

public class UserController {
	User user;
	Pub pub;
	Database db;
	Map<String, ClientAction> clientActions;
	private static String hashTag = "#";

	public UserController() {
		db = Database.getInstance();
		clientActions = new HashMap<>();
	}

	public boolean createNewUser(String userName, String pwd) {
		user = findUserByName(userName);
		if (user != null) {
			return false;
		} else {
			user = new User(userName, pwd);
			user.addFollowing(new Topic(user.getName()));
			db.signUpUser(user);
			return true;
		}
	}

	public boolean login(ClientAction clientAction) throws RemoteException {
		String userName = clientAction.getUserName();
		String pwd = clientAction.getPassword();
		user = findUserByName(userName);
		if (user != null && !user.isLogin()) {
			if (user.checkPwd(pwd)) {
				user.setIsLogin(true);
				setupSubs(clientAction);
				clientActions.put(userName, clientAction);
				return true;
			}
		}
		return false;
	}

	public boolean deconnecter(String name) {
		System.out.println(name + " deconnecte");
		user = findUserByName(name);
		if (user != null && user.isLogin()) {
			user.setIsLogin(false);
			int i = 1;
			for (Sub sub : user.getSubs()) {
				System.out.println("deconnecte sub " + i);
				sub.close();
			}
			clientActions.remove(name);
			System.out.println("deconnecte ok");
			return true;
		}
		System.out.println("Cannot find user");
		return false;
	}

	private void setupSubs(ClientAction clientAction) {
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
					user.addSub(new Sub(user.getName(), user.getName()
							+ topic.getTopicName(), topic.getTopicName(),
							clientAction));
				}
			}
		}
	}

	public void addFollowing(Topic topic, ClientAction clientAction) {
		try {
			user = findUserByName(clientAction.getUserName());
			if (user != null && user.isLogin() && !user.isFollowed(topic)) {
				user.addFollowing(topic);
				user.addSub(new Sub(user.getName(), user.getName()
						+ topic.getTopicName(), topic.getTopicName(),
						clientAction));
				if (clientAction != null) {
					clientAction
							.updateFollowings(getNbFollowing(user.getName()));
				}
				addFollower(topic, user, clientAction);

			}
			System.out.println("end add following");
		} catch (RemoteException e) {
			user = null;
			e.printStackTrace();
		}
	}

	private void addFollower(Topic topic, User user, ClientAction clientAction)
			throws RemoteException {
		User topicUser = findUserByName(topic.getTopicName());
		if (topicUser != null) {
			topicUser.addFollowers(user);
			ClientAction topicClientAction = clientActions.get(topicUser
					.getName());
			if (topicClientAction != null) {
				topicClientAction.updateFollowers(getNbFollower(topicUser
						.getName()));
			}
		}
	}

	public User findUserByName(String userName) {
		if (db.isExistUser(userName)) {
			return db.getUserByName(userName);
		}
		return null;
	}

	public int getNbFollowing(String userName) {
		user = findUserByName(userName);
		List<Topic> followings = user.getAllFollowing();
		if (followings != null && !followings.isEmpty()) {
			return followings.size()-1;
		}
		return 0;
	}

	public int getNbFollower(String userName) {
		user = findUserByName(userName);
		List<User> followers = user.getAllFollowers();
		if (followers != null) {
			return followers.size();
		}
		return 0;
	}

	public List<String> getAllUserName() {
		return db.getAllUserName();
	}

	public void publish(User owner, String topic, String tweetMessage,
			boolean firstTime) {
		new Pub().setupPublisher(owner, topic, tweetMessage);
		if (firstTime) {
			handleTweetMessage(owner, tweetMessage);
		}
	}

	private void handleTweetMessage(User owner, String tweetMessage) {
		System.out.println("start handle tweet message");
		ArrayList<String> tags = new ArrayList<>();
		String[] message = tweetMessage.split(" ");
		for (String word : message) {
			if (!word.isEmpty() && word.startsWith(hashTag)) {
				System.out.println("get tag: " + word);
				// add self as sub of hashtag
				addFollowing(new Topic(word),
						clientActions.get(owner.getName()));
				// send hashtag to topic
				publish(owner, word, tweetMessage, false);
			}
		}
		System.out.println("finish handling");
	}

	// public static void main(String[] args) {
	// UserController userController = new UserController();
	// userController.createNewUser("shi", "shi");
	// userController.login("shi", "shi");
	// userController.addFollowing(new Topic("polytechMac"));
	// }
}
