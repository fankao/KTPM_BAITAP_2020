package sa.app.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PSManage {
	private static PSManage instance = null;
	private TopicSession session;
	TopicConnectionFactory factory = null;
	TopicConnection connection = null;
	

	public PSManage() throws JMSException, NamingException {
		Context context = new InitialContext();
		factory = (TopicConnectionFactory) context.lookup("connectionFactoryName");
		connection = factory.createTopicConnection();
		connection.start();
		session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
	}

	public synchronized static PSManage getInstance() throws JMSException, NamingException {
		if (instance == null) {
			instance = new PSManage();
		}
		return instance;
	}
	
	public TopicSession getSesstion() {
		return session;
	}
}
