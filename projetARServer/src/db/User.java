package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends Topic implements Serializable{

	private static final long serialVersionUID = 1L;
	String name;
	String pwd;
	String profile;
	List<Topic> following;
	List<User> followers;
	
	public User(String name, String pwd){
		super(name);
		this.name = name;
		this.pwd = pwd;
		followers = new ArrayList<>();
		following = new ArrayList<>();
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
	public boolean addFollowing(Topic following){
		return this.following.add(following);
	}
	
	public boolean deleteFollowing(Topic following){
		return this.following.remove(following);
	}
	
	public List<Topic> getAllFollowing(){
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
	
}
