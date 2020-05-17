package sa.obj;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;

public class QueueReceiver {
	public static void main(String[] args) {
		ConnectionFactory factory = null;
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;
		
		Gson gson = new Gson();
		
		try {
			Context context = new InitialContext();
			factory = (ConnectionFactory) context.lookup("connectionFactoryName");
			connection = factory.createConnection();
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("ObjectQueue");
			
			consumer = session.createConsumer(destination);
			
			System.out.println("Receiver was listened on queue...");
			
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					try {
						if(message instanceof TextMessage) {
							TextMessage textMsg = (TextMessage) message;
							
							Person person = gson.fromJson(textMsg.getText(),Person.class);
							
							System.out.println("Recevied: "+person.toString());
						}else if (message instanceof ObjectMessage) {
							ObjectMessage objMsg = (ObjectMessage) message;
							System.out.println("Received obj: "+gson.toJson(objMsg, ObjectMessage.class));
						}
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
