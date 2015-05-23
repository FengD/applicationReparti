package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tweet implements Serializable {

	private static final long serialVersionUID = 1L;
	User owner;
	String message;
	
	List<String> photos;
	List<Topic> topics;
	List<User> mentionUsers;
	
	public Tweet(User owner, String message){
		this.message = message;
		photos = new ArrayList<>();
		topics = new ArrayList<>();
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
	
	//topic add delete getAll
	public boolean addTopic(Topic topic){
		return this.topics.add(topic);
	}
	
	public boolean deleteTopic(Topic topic){
		return this.topics.remove(topic);
	}
	
	public List<Topic> getAllTopics(){
		return this.topics;
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
