package sa.demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;

public class MyPublisher {
	private TopicConnection connection = null;
	private TopicSession session = null;
	private Topic topic = null;

	public MyPublisher() throws JMSException, NamingException {
		Context context = new InitialContext();
		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("MyTopic");
		connection = factory.createTopicConnection();
		session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("MyTopic");
		connection.start();
	}

	@SuppressWarnings("unused")
	private void exit() throws JMSException {
		session.close();
		connection.close();
	}

	private void sendMessage(SinhVien sv) throws JMSException {
		Gson gson = new Gson();
		String json = gson.toJson(sv);
		TopicPublisher publisher = session.createPublisher(topic);
		Message message = session.createTextMessage(json);
		publisher.send(message);
	}

	public static void main(String[] args) {
		SinhVien sv = new SinhVien(01420011, "Nguyễn Văn Lành", 30);
		try {
			MyPublisher sender = new MyPublisher();
			sender.sendMessage(sv);
			sender.exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
