package controller;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;

import client.controller.ClientAction;
import db.Tweet;
import db.User;

public class Pub {
	static private Topic topic;
	static private TopicPublisher topicPublisher;
	static InitialContext context;
	static TopicConnectionFactory topicConnectionFactory;
	static TopicConnection topicConnection;
	static TopicSession topicSession;
	static String topicConnectionFactoryName = "ConnectionFactory";
	
	
	static void setup(String topicName){
		try {
        	Hashtable<String, String> properties = new Hashtable<String, String>();
        	properties.put(Context.INITIAL_CONTEXT_FACTORY, 
        	    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        	properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
			context = new InitialContext(properties);
			
			topicConnectionFactory = (TopicConnectionFactory)context.lookup(topicConnectionFactoryName);
			topicConnection = topicConnectionFactory.createTopicConnection();
			topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);

			topic = (Topic) context.lookup("dynamicTopics/"+topicName);
//			topic = topicSession.createTopic(topicName);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static void setupPublisher(User owner, String topicName, String tweetMessage){	 
		try {
			 setup(topicName);
			 topicPublisher = topicSession.createPublisher(topic);
			 topicPublisher.setDeliveryMode(DeliveryMode.PERSISTENT);
			 topicConnection.start();
			 //send messagge
			 Tweet tweet = new Tweet(owner,tweetMessage);
			 publish(tweet);
			 close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	
	static void publish(Tweet tweet) {
		ObjectMessage message;
		try {
			message = topicSession.createObjectMessage(tweet);
			topicPublisher.publish(message);
			System.out.println(tweet.getMessage());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	static void close() throws JMSException{
		 System.out.println("Close session");
		 topicSession.close();
		 System.out.println("Close connection");
		 topicConnection.close();
	}
//	
//	public static void main(String[] args) {
//		Pub.setupPublisher("polytechMac");
//	}
}
