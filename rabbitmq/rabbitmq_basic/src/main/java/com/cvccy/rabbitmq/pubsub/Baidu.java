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
        /**定义交换机 exchange:交换机的名称
         * type:交换机的类型 常见的有direct,fanout,topic等
         * durable:设置是否持久化。durable设置为true时表示持久化，反之非持久化.持久化可以将交换器存入磁盘，在服务器重启的时候不会丢失相关信息
         * autodelete:设置是否自动删除。autoDelete设置为true时，则表示自动删除。自动删除的前提是至少有一个队列或者交换器与这个交换器绑定，之后，所有与这个交换器绑定的队列或者交换器都与此解绑。
         * 不能错误的理解—当与此交换器连接的客户端都断开连接时，RabbitMq会自动删除本交换器
         * arguments:其它一些结构化的参数，比如：alternate-exchange
         */
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER,"fanout",true,false,null);
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
