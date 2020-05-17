package sa.app.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSManage {
	private static JMSManage instance = null;
	private Session session;
	ConnectionFactory factory = null;
	Connection connection = null;

	public JMSManage() throws Exception {
		ConnectionFactory factory = null;
		Connection connection = null;

		Context context = new InitialContext();
		factory = (ConnectionFactory) context.lookup("connectionFactoryName");
		connection = factory.createConnection();
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

	}

	public synchronized static JMSManage getInstance() throws Exception {
		if (instance == null) {
			instance = new JMSManage();
		}
		return instance;
	}

	public Session getSesstion() {
		return session;
	}
	
	public void close() {
		try {
			this.connection.close();
			this.session.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
