package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	/**
	 * Check if the user is already sign up
	 * @param userName
	 * @return is Exist or not
	 */
	public boolean isExistUser(String userName){
		return this.users.containsKey(userName);
	}
	
	/**
	 * get user in db by name
	 * @param name
	 * @return user
	 */
	public User getUserByName(String name){
		return this.users.get(name);
	}
	
	public List<String> getAllUserName(){
		List<String> result = new ArrayList<>(users.keySet());
		return result;
	}
	
	//tweet add delete
	public void addTweet(Tweet tweet){
		this.tweets.add(tweet);
	}
	
	public void deleteTweet(Tweet tweet){
		this.tweets.remove(tweet);
	}
	
	//topic add delete get
	/**
	 * 
	 * @param topic
	 * @return if it is a new topic return true else false
	 */
	public boolean addTopic(Topic topic){
		if (!this.topics.containsKey(topic.getTopicName())) {
			this.topics.put(topic.getTopicName(), topic);
			return true;
		}	
		return false;
	}
	
	public void deleteTopic(String topic){
		this.topics.remove(topic);
	}
	
	public Topic getTopic(String topic){
		return this.topics.get(topic);
	}
	
	public List<String> getAllTopic(){
		List<String> result = new ArrayList<>(topics.keySet());
		return result;
	}
}
