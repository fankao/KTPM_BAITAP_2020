package sa.topic;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PSManager {
	private static PSManager instance = null;
	TopicConnectionFactory connectionFactory = null;
	TopicConnection connection = null;
	TopicSession session = null;
	Topic topic = null;

	public PSManager() throws JMSException, NamingException {
		Context context = new InitialContext();
		connectionFactory = (TopicConnectionFactory) context.lookup("connectionFactoryName");
		connection = connectionFactory.createTopicConnection();
		session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic("ChatTopic");
		connection.start();

	}

	public synchronized static PSManager getInstance() throws JMSException, NamingException {
		if (instance == null) {
			instance = new PSManager();
		}
		return instance;
	}

	public void sendMessage(String msg) throws JMSException {
		TopicPublisher publisher = session.createPublisher(topic);
		publisher.send(session.createTextMessage(msg));
		System.out.println("Sent message: " + msg);
	}

	public TopicSubscriber getSubscriber() throws JMSException {
		return session.createSubscriber(topic);
	}
}
