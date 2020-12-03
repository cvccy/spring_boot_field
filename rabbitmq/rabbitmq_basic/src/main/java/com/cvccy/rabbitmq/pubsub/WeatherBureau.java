package com.cvccy.rabbitmq.pubsub;

import com.cvccy.rabbitmq.util.RabbitConstant;
import com.cvccy.rabbitmq.util.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xhf
 * @time 20201203
 * @discription Public And Subscription
 */
public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel  = connection.createChannel();
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER,"fanout",true,false,null);
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"",null,"runing".getBytes());
        channel.close();
        connection.close();
    }
}
