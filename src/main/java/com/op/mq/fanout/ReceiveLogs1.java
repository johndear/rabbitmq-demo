package com.op.mq.fanout;

/**
 * @author hushuang
 *
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs1 {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
		
		// 消息确认机制
		//		Consumer consumer = new DefaultConsumer(channel) {
//			@Override
//			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//				String message = new String(body, "UTF-8");
//				try{
//					System.out.println(" [x] Received '" + message + "'");
//					// false：表示从队列中删除（包括Unacked和Total）
//					channel.basicAck(envelope.getDeliveryTag(), false);
//				} catch(Exception e){
//					// true：表示将消息重新放入到队列中，false：表示直接从队列中删除(等同于：channel.basicAck(envelope.getDeliveryTag(), false);)
//					channel.basicReject(envelope.getDeliveryTag(), true);
//				}
//			}
//		};
//		channel.basicConsume(queueName, false, consumer);
	}
}
