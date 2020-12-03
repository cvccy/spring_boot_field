package com.cvccy.rabbitmq.pubsub;

import com.cvccy.rabbitmq.util.RabbitConstant;
import com.cvccy.rabbitmq.util.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;


/**
 * @author xhf
 * @time 20201203
 * @discription Public And Subscription
 */
public class Baidu {
    public static void main(String[] args) throws IOException {
        //获取长链接
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_BAIDU,true,false,false,null);
        //queueBind用于将队列与交换机绑定
        //参数1：队列名 参数2：交互机名  参数三：路由key（暂时用不到)
        channel.queueBind(RabbitConstant.QUEUE_BAIDU, RabbitConstant.EXCHANGE_WEATHER, "");
        channel.basicConsume(RabbitConstant.QUEUE_BAIDU,true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println(body.toString());
            }
        });



    }
}
