package controller;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;

import db.Tweet;

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
	
	static void setupPublisher(String topicName){	 
		try {
			 setup(topicName);
			 topicPublisher = topicSession.createPublisher(topic);
			 topicPublisher.setDeliveryMode(DeliveryMode.PERSISTENT);
			 topicConnection.start();
			 //send messagge
			 publish();
			 System.out.println("Close session");
			 topicSession.close();
			 System.out.println("Close connection");
			 topicConnection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	
	static void publish() {
//        TextMessage tm;
		ObjectMessage message;
		try {
			Tweet tweet = new Tweet("small bird king");
			message = topicSession.createObjectMessage(tweet);
//			message.setObjectProperty("tweet", );
			topicPublisher.publish(message);
			System.out.println(tweet.getMessage());
//			tm = topicSession.createTextMessage("A text msg, now="+System.currentTimeMillis());
//	        topicPublisher.publish(tm);
//	        System.out.println("sendAsync, sent text=" +  tm.getText());
//	        topicPublisher.close();
//	        System.out.println("End sendAsync");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Pub.setupPublisher("polytechMac");
	}
}
