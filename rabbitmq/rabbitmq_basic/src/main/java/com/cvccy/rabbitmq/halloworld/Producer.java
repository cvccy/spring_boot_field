package com.cvccy.rabbitmq.halloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xhf
 * @Time 20201202
 * @discription 消息生产者
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1. 获取TCP长链接
        ConnectionFactory factory = new ConnectionFactory();
        //1.1 设置属性
        factory.setHost("cvccy.com");
        factory.setPort(5672);
        factory.setUsername("cvccy");
        factory.setPassword("************");
        factory.setVirtualHost("20201202");
        Connection connection = factory.newConnection();
        //2. 创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = connection.createChannel();
        //3. 建队列,声明并创建一个队列，如果队列已存在，则使用这个队列
        //创建队列,声明并创建一个队列，如果队列已存在，则使用这个队列
        //第一个参数：队列名称ID
        //第二个参数：是否持久化，false对应不持久化数据，MQ停掉数据就会丢失
        //第三个参数：是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
        //第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
        //其他额外的参数, null
        channel.queueDeclare("helloworld",false,false,false,null);
        //4. 发布消息
        //四个参数
        //exchange 交换机，暂时用不到，在后面进行发布订阅时才会用到
        //队列名称
        //额外的设置属性
        //最后一个参数是要传递的消息字节数组
        for(int i=0;i<10;i++){
            Thread.sleep(2000);
            channel.basicPublish("","helloworld",null,"xiaxi".getBytes());
        }

        //5. 关闭链接
        channel.close();
        connection.close();
    }

}
