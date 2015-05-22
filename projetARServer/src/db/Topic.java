package db;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

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
