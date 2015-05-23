package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Sub;

public class User extends Topic implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String pwd;
	private String profile;
	private List<Topic> following;
	private List<User> followers;
	private List<Sub> subList;
	private boolean isLogin;
	
	public User(String name, String pwd){
		super(name);
		this.name = name;
		this.pwd = pwd;
		isLogin = false;
		followers = new ArrayList<>();
		following = new ArrayList<>();
		subList = new ArrayList<>();
	}
	
	public User(String name, String pwd, String profile){
		this(name,pwd);
		this.profile = profile;
	}
	//sub active list
	public List<Sub> getSubs(){
		return this.subList;
	}
	
	public void setSubs(List<Sub> subs){
		this.subList = subs;
	}
	public boolean addSub(Sub sub){
		return this.subList.add(sub);
	}
	
	
	//isLogin()
	public boolean isLogin(){
		return this.isLogin;
	}
	
	public void setIsLogin(boolean isLogin){
		this.isLogin = isLogin;
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
