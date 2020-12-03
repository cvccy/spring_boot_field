package com.cvccy.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



public class RabbitUtils {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    static {
        connectionFactory.setHost("cvccy.com");
        connectionFactory.setPort(5672);//5672是RabbitMQ的默认端口号
        connectionFactory.setUsername("cvccy");
        connectionFactory.setPassword("hf3|uVhf3|uVS9v@NaS9v@Na");
        connectionFactory.setVirtualHost("20201202");
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = connectionFactory.newConnection();
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
