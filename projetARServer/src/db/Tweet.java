package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tweet implements Serializable {

	private static final long serialVersionUID = 1L;
	User owner;
	String message;
	
	List<String> photos;
	Topic topic;
	List<User> mentionUsers;
	
	public Tweet(User owner, String tweetMessage, String topic){
		this.message = tweetMessage;
		this.owner = owner;
		photos = new ArrayList<>();
		this.topic = new Topic(topic);
		mentionUsers = new ArrayList<>();
	}

	
	//get/set owner
	public void setOwner(User owner){
		this.owner = owner;
	}
	
	public User getOwner(){
		return this.owner;
	}
	
	public String getOwnerName(){
		return this.owner.getName();
	}
	
	//message get set
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	//photo add delete getAll
	public boolean addPhoto(String photo){
		return this.photos.add(photo);
	}
	
	public boolean deletePhoto(String photo){
		return this.photos.remove(photo);
	}
	
	public List<String> getAllPhotos(){
		return this.photos;
	}
	
	//topic set get
	public void setTopic(Topic topic){
		this.topic = topic;
	}
	
	public Topic getTopic(){
		return this.topic;
	}
	
	//mention user add delete getAll
	public boolean addMentionUser(User mentionUser){
		return this.mentionUsers.add(mentionUser);
	}
	
	public boolean deleteMentionUser(User mentionUser){
		return this.mentionUsers.remove(mentionUser);
	}
	
	public List<User> getAllMentionUsers(){
		return this.mentionUsers;
	}
}
