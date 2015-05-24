package controller;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import client.controller.ClientAction;
import db.Tweet;

public class Sub implements javax.jms.MessageListener, Serializable {

	private static final long serialVersionUID = 1L;
	String owner;
	String subName;
	private Topic topic;
	private TopicSubscriber topicSubscriber;
	InitialContext context;
	TopicConnectionFactory topicConnectionFactory;
	TopicConnection topicConnection;
	TopicSession topicSession;
	String topicConnectionFactoryName = "ConnectionFactory";

	private ClientAction clientAction;

	public Sub(String owner, String subName, String topicName, ClientAction clientAction) {
		this.owner = owner;
		this.subName = subName;
		this.clientAction = clientAction;
		setupSouscripteur(topicName);
	}

	void setup(String topicName) {
		try {
			Hashtable<String, String> properties = new Hashtable<String, String>();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
			context = new InitialContext(properties);

			topicConnectionFactory = (TopicConnectionFactory) context
					.lookup(topicConnectionFactoryName);
			topicConnection = topicConnectionFactory.createTopicConnection(
					"admin", "admin");
			// should set a clientID for durable subscriber
			topicConnection.setClientID(subName);

			topic = (Topic) context.lookup("dynamicTopics/" + topicName);// ""
			// topic = topicSession.createTopic("polytech");

			System.out.println("topic name:" + topicName);
			// topic = topicSession.createTopic(topicName);

			topicSession = topicConnection.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	void setupSouscripteur(String topicName) {
		try {
			setup(topicName);
			topicConnection.start();

			topicSubscriber = topicSession.createDurableSubscriber(topic,
					subName);
			topicSubscriber.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			topicSession.close();
			topicConnection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			try {
				ObjectMessage objectMessage = (ObjectMessage) message;
				Tweet tweet = (Tweet) objectMessage.getObject();
				System.out.println("recept tweet: " + tweet.getMessage());
				HashMap<String, String> hashMap = new HashMap<>();
				String owner = tweet.getOwnerName();
				String topic = tweet.getTopic().getTopicName();
				//receive message send by others or your message but topic is your name
				if (!this.owner.equals(owner)
						|| (this.owner.equals(owner) && this.owner.equals(topic))) {
					hashMap.put("message", tweet.getMessage());
					hashMap.put("owner", tweet.getOwnerName());
					hashMap.put("topic", tweet.getTopic().getTopicName());
					try {
						clientAction.newTweets(hashMap);
						System.out.println("notify new tweet");
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

//	public static void main(String[] args) {
//		new Sub("pub", "pub", null);
//	}

}
