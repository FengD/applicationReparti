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
	private static String host="127.0.0.1";
	
	static void setup(String topicName){
		try {
			System.out.println("start setup");
        	Hashtable<String, String> properties = new Hashtable<String, String>();
        	properties.put(Context.INITIAL_CONTEXT_FACTORY, 
        	    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        	properties.put(Context.PROVIDER_URL, "tcp://"+host+":61616");
			context = new InitialContext(properties);
			
			System.out.println("start creating factory");
			topicConnectionFactory = (TopicConnectionFactory)context.lookup(topicConnectionFactoryName);
			System.out.println("create factory");
			topicConnection = topicConnectionFactory.createTopicConnection();
			System.out.println("create connection");
			topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			System.out.println("create session");

			topic = (Topic) context.lookup("dynamicTopics/"+topicName);
			System.out.println("create topic");
//			topic = topicSession.createTopic(topicName);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static void setupPublisher(User owner, String topicName, String tweetMessage){	 
		try {
			 System.out.println("start setup publisher");
			 setup(topicName);
			 System.out.println("end setup");
			 topicPublisher = topicSession.createPublisher(topic);
			 System.out.println("create publisher");
			 topicPublisher.setDeliveryMode(DeliveryMode.PERSISTENT);
			 topicConnection.start();
			 System.out.println("connection start");
			 //send messagge
			 Tweet tweet = new Tweet(owner,tweetMessage);
			 publish(tweet);
			 close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	
	static void publish(Tweet tweet) {
		System.out.println("publish start");
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
	
	public static void main(String[] args) {
		Pub.setupPublisher(new User("pub", "pub"),"pub","hello pub");
	}
}
