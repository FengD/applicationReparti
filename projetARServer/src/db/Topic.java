package db;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

public class Topic implements Serializable{

	private static final long serialVersionUID = 1L;
	String topic;
	Stack<Tweet> tweets;
	
	public Topic(String topic){
		this.topic = topic;
		tweets = new Stack<>();
	}
	
	//topic get
	public String getTopicName(){
		return this.topic;
	}
	
	//tweets add delete getAll
	public boolean addTweet(Tweet t){
		return this.tweets.add(t);
	}
	
	public boolean deleteTweet(Tweet t){
		return this.tweets.remove(t);
	}
	
	public List<Tweet> getAllTweets(){
		return this.tweets;
	}
}
