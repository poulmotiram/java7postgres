package com.sample;

import javax.jms.DeliveryMode;
import javax.jms.MapMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsPublisher {
	
	public static final String BROKER_URL = "tcp://localhost:61616";
	public static final String DESTINATION = "info.topic";
	
	public static void main(String[] args) throws Exception {
		run();
	}
	
	public static void run() throws Exception{
		
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
		
		TopicConnection connection = factory.createTopicConnection();
		connection.start();
		TopicSession session = connection.createTopicSession(true, TopicSession.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(DESTINATION);
		
		
		javax.jms.TopicPublisher publisher = session.createPublisher(topic);
		publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
		MapMessage map = session.createMapMessage();
		map.setString("name", "kevin");
		map.setInt("age", 26);
		publisher.send(map);
		session.commit();
		
		session.close();
		connection.close();
	}
}
