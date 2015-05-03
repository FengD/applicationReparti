package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	String name;
	String pwd;
	String profile;
	List<User> following;
	List<User> followers;
	Stack<Tweet> tweets;
	
	public User(String name, String pwd){
		this.name = name;
		this.pwd = pwd;
		followers = new ArrayList<>();
		following = new ArrayList<>();
		tweets = new Stack<>();
	}
	
	public User(String name, String pwd, String profile){
		this(name,pwd);
		this.profile = profile;
	}
	
	//name set get
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	//pwd set check
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	
	public boolean checkPwd(String pwd){
		if (this.pwd != null) {
			if (this.pwd.compareTo(pwd) == 0) {
				return true;
			}
		}
		return false;
	}
	
	//profile get set
	public void setProfile(String profile){
		this.profile = profile;
	}
	
	public String getProfile(){
		return this.profile;
	}
	
	//following add delete getAll
	public boolean addFollowing(User following){
		return this.following.add(following);
	}
	
	public boolean deleteFollowing(User following){
		return this.following.remove(following);
	}
	
	public List<User> getAllFollowing(){
		return this.following;
	}
	
	//followers add delete getAll
	public boolean addFollowers(User follower){
		return this.followers.add(follower);
	}
	
	public boolean deleteFollowers(User follower){
		return this.followers.remove(follower);
	}
	
	public List<User> getAllFollowers(){
		return this.followers;
	}
	
	//tweet add delete getAll
	public boolean addTweet(Tweet tweet){
		return this.tweets.add(tweet);
	}
	
	public boolean deleteTweet(Tweet tweet){
		return this.tweets.remove(tweet);
	}
	
	public Stack<Tweet> getAllTweets(){
		return this.tweets;
	}
}
