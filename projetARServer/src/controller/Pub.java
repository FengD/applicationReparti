package controller;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import client.controller.ClientAction;
import db.Tweet;
import db.User;

public class Pub {
	private Topic topic;
	private TopicPublisher topicPublisher;
	InitialContext context;
	TopicConnectionFactory topicConnectionFactory;
	TopicConnection topicConnection;
	TopicSession topicSession;
	private static String topicConnectionFactoryName = "ConnectionFactory";
	private static String host="localhost";
	
	public Pub(){
		
	}
	
	void setup(String topicName){
		try {
//			System.out.println("start setup");
        	Hashtable<String, String> properties = new Hashtable<String, String>();
        	properties.put(Context.INITIAL_CONTEXT_FACTORY, 
        	    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        	properties.put(Context.PROVIDER_URL, "tcp://"+host+":61616");
			context = new InitialContext(properties);
			
			System.out.println("start creating factory");
			topicConnectionFactory = (TopicConnectionFactory)context.lookup(topicConnectionFactoryName);
//			topicConnectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
//			System.out.println("create factory");
			topicConnection = topicConnectionFactory.createTopicConnection();
//			System.out.println("create connection");
			topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
//			System.out.println("create session");

//			topic = (Topic) context.lookup("dynamicTopics/"+topicName);
			topic = topicSession.createTopic(topicName);
//			System.out.println("create topic");		
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void setupPublisher(User owner, String topicName, String tweetMessage){	 
		try {
//			 System.out.println("start setup publisher");
			 setup(topicName);
//			 System.out.println("end setup");
			 topicPublisher = topicSession.createPublisher(topic);
//			 System.out.println("create publisher");
			 topicPublisher.setDeliveryMode(DeliveryMode.PERSISTENT);
			 topicConnection.start();
//			 System.out.println("connection start");
			 //send messagge
			 Tweet tweet = new Tweet(owner,tweetMessage,topicName);
			 publish(tweet);
			 close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	
	void publish(Tweet tweet) {
		System.out.println("publish start");
		ObjectMessage message;
		try {
			message = topicSession.createObjectMessage(tweet);
			topicPublisher.publish(message);
			System.out.println("publish "+tweet.getMessage());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	void close() throws JMSException{
		 System.out.println("Close session");
		 topicSession.close();
		 System.out.println("Close connection");
		 topicConnection.close();
	}
	
	public static void main(String[] args) {
		new Pub().setupPublisher(new User("pub", "pub"),"pub","hello pub6 ");
	}
}
