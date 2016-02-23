package com.sample;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsSubscriber {

	public static final String BROKER_URL = "tcp://localhost:61616";
	public static final String DESTINATION = "info.topic";

	public static void main(String[] args) throws Exception {
		run();
	}

	public static void run() throws Exception {

		TopicConnectionFactory factory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);

		TopicConnection connection = factory.createTopicConnection();
		connection.setClientID("myClientId1");
		connection.start();
		
		TopicSession session = connection.createTopicSession(true,
				Session.AUTO_ACKNOWLEDGE);
		try {
			Topic topic = session.createTopic(DESTINATION);
			javax.jms.TopicSubscriber subscriber = session
					.createDurableSubscriber(topic, "mysub");

			subscriber.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					System.out.println("message received ");
					if (message instanceof MapMessage) {
						try {
							MapMessage map = (MapMessage) message;
							String name = map.getString("name");
							Integer age = map.getInt("age");
							System.out.println("name=" + name + ",age=" + age);
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
			Thread.currentThread().sleep(1000 * 10);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
			session.close();
		}

	}
}
