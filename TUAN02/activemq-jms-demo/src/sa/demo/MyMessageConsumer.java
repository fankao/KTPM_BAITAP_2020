package sa.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MyMessageConsumer {
	public static void main(String[] args) {
		ConnectionFactory factory = null;
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;
		TextMessage message = null;
		
		try {
			Context context = new InitialContext();
			factory = (ConnectionFactory) context.lookup("connectionFactoryName");

			connection = factory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("MyQueue");
			
			consumer = session.createConsumer(destination);
			
			message = (TextMessage) consumer.receive();
			
			System.out.println("Received message: "+message.getText());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
