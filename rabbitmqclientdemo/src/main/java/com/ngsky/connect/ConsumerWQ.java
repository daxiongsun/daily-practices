package com.ngsky.connect;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/***
 * @desc Work Queues
 */
public class ConsumerWQ {

    private static final String TASK_QUEUE_NAME = "new_queue";

    public static void main(String[] argv) {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;

        factory.setHost("172.16.40.91");
        factory.setUsername("newng");
        factory.setPassword("newng");
        factory.setVirtualHost("newng");

        try {
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            new Thread() {
                public void run() {
                    Consumer consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            System.out.println("consumer1:========" + "consumerTag:" + consumerTag + ",envelope:" + envelope + ",properties:" + properties +
                                    ",body:" + new String(body, "UTF-8"));
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                    super.handleDelivery(consumerTag, envelope, properties, body);
                            channel.basicAck(envelope.getDeliveryTag(), false);
                        }
                    };

                    try {
                        while (true) {
                            String result = channel.basicConsume(TASK_QUEUE_NAME, false, consumer);  // 第二个参数表示手动确认是否消费成功
                            System.out.println("consumer1:" + result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

            new Thread() {
                public void run() {
                    Consumer consumer2 = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            System.out.println("consumer2:========" + "consumerTag:" + consumerTag + ",envelope:" + envelope + ",properties:" + properties +
                                    ",body:" + new String(body, "UTF-8"));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                    super.handleDelivery(consumerTag, envelope, properties, body);
                            channel.basicAck(envelope.getDeliveryTag(), false);
                        }
                    };

                    try {
                        while (true) {
                            String result = channel.basicConsume(TASK_QUEUE_NAME, false, consumer2);
                            System.out.println("consumer2:" + result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
