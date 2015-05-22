package webservice;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sub implements javax.jms.MessageListener {
	
	String subName;
	private Topic topic;
	private TopicSubscriber topicSubscriber;
	InitialContext context;
	TopicConnectionFactory topicConnectionFactory;
	TopicConnection topicConnection;
	TopicSession topicSession;
	String topicConnectionFactoryName = "ConnectionFactory";
	
	public Sub(String name,String topicName) {
		this.subName = name;
		setupSouscripteur(topicName);
	}
	
	void setup(String topicName){
		try {
        	Hashtable properties = new Hashtable();
        	properties.put(Context.INITIAL_CONTEXT_FACTORY, 
        	    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        	properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
			context = new InitialContext(properties);
			
			topicConnectionFactory = (TopicConnectionFactory)context.lookup(topicConnectionFactoryName);
			topicConnection = topicConnectionFactory.createTopicConnection("user","pwd");
			//should set a clientID for durable subscriber
			topicConnection.setClientID(subName);
		
			topic = (Topic) context.lookup("dynamicTopics/polytech");//""
//			topic = topicSession.createTopic("polytech");
			
			topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	void setupSouscripteur(String topicName){	 
		try {
			 setup(topicName);
			 topicConnection.start();
	
			 /********************no durable subscriber can receive message*******************************/
//			 topicSubscriber = topicSession.createSubscriber(topic);
			 topicSubscriber = topicSession.createDurableSubscriber(topic,subName);
//			 topicSubscriber.setMessageListener(this);

			 System.out.println("start while");
			 
		     while (true){
		    	System.out.println("in while");
		    	MapMessage m= (MapMessage)topicSubscriber.receive(10000);
		    	
		    	
		    	
		        if (m == null) {
					System.out.println("No more messages");
					break;
				}
		        else {
		        	System.out.print("recept synch: "); 
		        	onMessage(m);
				}
		     }
		     System.out.println("end while");
		     topicConnection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void onMessage(Message message) {
		System.out.println("on message");
		try {
			System.out.print("Recu un message du topic: "+((MapMessage)message).getString("nom"));
			System.out.println(((MapMessage)message).getString("num"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Sub("shi", "polytech");	
	}

}
