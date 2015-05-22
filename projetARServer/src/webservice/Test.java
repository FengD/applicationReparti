package webservice;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSubscriber;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *  A JMS client example program that synchronously receives a message a Topic
 *     
 *  @author Scott.Stark@jboss.org
 *  @version $Revision: 1.9 $
 */
public class Test
{
    TopicConnection conn = null;
    TopicSession session = null;
    Topic topic = null;
    
    public void setupPubSub()
        throws JMSException, NamingException
    {
    	Hashtable properties = new Hashtable();
    	properties.put(Context.INITIAL_CONTEXT_FACTORY, 
    	    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
    	properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
    	
        InitialContext iniCtx = new InitialContext(properties);
        Object tmp = iniCtx.lookup("ConnectionFactory");

        TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        conn = tcf.createTopicConnection("john", "needle");
        conn.setClientID("clientID");
        topic = (Topic) iniCtx.lookup("dynamicTopics/testTopic");

        session = conn.createTopicSession(false,
                                          TopicSession.AUTO_ACKNOWLEDGE);
        conn.start();
    }
    
    public void recvSync()
        throws JMSException, NamingException
    {
        System.out.println("Begin recvSync");
        // Setup the pub/sub connection, session
        setupPubSub();
        // Wait upto 5 seconds for the message
        TopicSubscriber recv = session.createDurableSubscriber(topic, "jms-ex1dtps");
        Message msg = recv.receive(5000);
        if (msg == null) {
            System.out.println("Timed out waiting for msg");
        } else {
            System.out.println("DurableTopicRecvClient.recv, msgt=" + msg);
        } 
    }
    
    public void stop() 
        throws JMSException
    {
        conn.stop();
        session.close();
        conn.close();
    }
    
    public static void main(String args[]) 
        throws Exception
    {
        System.out.println("Begin DurableTopicRecvClient, now=" + 
                           System.currentTimeMillis());
        Test client = new Test();
        client.recvSync();
        client.stop();
        System.out.println("End DurableTopicRecvClient");
        System.exit(0);
    }
}
    