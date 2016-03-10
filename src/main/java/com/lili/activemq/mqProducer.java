package com.lili.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 前提：下载activemq 启动bin/.bat 然后运行producer 
 * 若是使用topic，先运行consumer，进行订阅才能收到消息，然后运行producer
 * @author jh
 *
 */
public class mqProducer {

	private static final String USER = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SEND_NUMBER = 5;

	public static void main(String[] args) throws JMSException {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁
		Destination destination;
		// MessageProducer：消息发送者
		MessageProducer producer;

		connectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, URL);

		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			connection.start();
			// 获取操作连接
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("FirstQUeue");
			
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			sendMessage(session, producer);
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
		for (int i = 0; i < SEND_NUMBER; i++) {
			TextMessage message = session.createTextMessage("ActiveMq 发送的消息" + i);
			// 发送消息到目的地方
			System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
			producer.send(message);
		}
	}
}
