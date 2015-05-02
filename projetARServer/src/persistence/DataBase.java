package persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import rmi.Tweeter;

public class DataBase {
	private static Map<String, User> dataBase = new HashMap<String, User>();
	private static Stack<Tweeter> tweets = new Stack<Tweeter>();
	private static Map<String, Topic> topics = new HashMap<String, Topic>();

	public static Map<String, Topic> getTopics() {
		return topics;
	}

	public static void setTopics(Map<String, Topic> topics) {
		DataBase.topics = topics;
	}

	public static Stack<Tweeter> getTweets() {
		return tweets;
	}

	public static void setTweets(Stack<Tweeter> tweets) {
		DataBase.tweets = tweets;
	}

	public static Map<String, User> getDataBase() {
		return dataBase;
	}

	public static void setDataBase(Map<String, User> dataBase) {
		DataBase.dataBase = dataBase;

	}

}
