package sa.obj;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;

public class QueueSender {
	public static void main(String[] args) {
		ConnectionFactory factory = null;
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
		Message message = null;

		Gson gson = new Gson();

		try {
			Context context = new InitialContext();
			factory = (ConnectionFactory) context.lookup("connectionFactoryName");
			connection = factory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("ObjectQueue");

			producer = session.createProducer(destination);
			message = session.createTextMessage("Hello message from ActiveMQ");

			producer.send(message);

			// Send object
			Person person = new Person(1, "Minh Chien", new Date(1999, 3, 2));
			String json = gson.toJson(person);
			message = session.createTextMessage(json);
			
			producer.send(message);
			
			System.out.println("Sent: "+((TextMessage) message).getText());

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				producer.close();
				session.close();
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
