package db;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Database {
	private Map<String, User> users;
	private Stack<Tweet> tweets;
	private Map<String, Topic> topics;
	
	private static Database INSTANCE;
	
	private Database(){
		this.users = new HashMap<String,User>();
		this.tweets = new Stack<Tweet>();
		this.topics = new HashMap<String, Topic>();
	}
	
	/**
	 * singleton database
	 * @return database
	 */
	public static Database getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new Database();
		}
		return INSTANCE;
	}
	
	//create new user
	public void signUpUser(User user){
		this.users.put(user.getName(), user);
	}
	
	//tweet add delete
	public void addTweet(Tweet tweet){
		this.tweets.add(tweet);
	}
	
	public void deleteTweet(Tweet tweet){
		this.tweets.remove(tweet);
	}
	
	//topic add delete get
	public void addTopic(Topic topic){
		this.topics.put(topic.getTopicName(), topic);
	}
	
	public void deleteTopic(String topic){
		this.topics.remove(topic);
	}
	
	public Topic getTopic(String topic){
		return this.topics.get(topic);
	}
}
