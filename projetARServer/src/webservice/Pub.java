package webservice;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;

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
        	Hashtable properties = new Hashtable();
        	properties.put(Context.INITIAL_CONTEXT_FACTORY, 
        	    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        	properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
			context = new InitialContext(properties);
			
			topicConnectionFactory = (TopicConnectionFactory)context.lookup(topicConnectionFactoryName);
			topicConnection = topicConnectionFactory.createTopicConnection();
			topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
//			topic = topicSession.createTopic("polytech");
			topic = (Topic) context.lookup("dynamicTopics/polytech");
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
//			 topicPublisher.setDeliveryMode(DeliveryMode.PERSISTENT);
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
        TextMessage tm;
		try {
			tm = topicSession.createTextMessage("A text msg, now="+System.currentTimeMillis());
	        topicPublisher.publish(tm);
	        System.out.println("sendAsync, sent text=" +  tm.getText());
	        topicPublisher.close();
	        System.out.println("End sendAsync");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//	  	for (int i=1;i<=10;i++){
//    		try {
//    			//Fabriquer un message
//        		MapMessage mess = topicSession.createMapMessage();
//        		mess.setInt("num",i);
//        		mess.setString("nom",i+"-");
//        		if (i%2==0)
//        			mess.setStringProperty("typeMess","important");
//        		if (i==1) mess.setIntProperty("numMess",1);
//        		//Poster ce message dans la queue
//				topicPublisher.send(mess);
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}
//    	}
	}
	
	public static void main(String[] args) {
		Pub.setupPublisher("polytech");
	}
//	static void setupSubscriber(String topicConnectionFactoryName, String topicName){
//		 setUp(topicConnectionFactoryName, topicName);
//		 topicSubscriber = topicSession.createSubscriber(topic);
//		 topicConnection.start();
//	}
}
