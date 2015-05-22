package db;

import java.io.Serializable;

public class Topic implements Serializable{

	private static final long serialVersionUID = 1L;
	String topic;
	
	public Topic(String topic){
		this.topic = topic;
	}
	
	//topic get
	public String getTopicName(){
		return this.topic;
	}
}
