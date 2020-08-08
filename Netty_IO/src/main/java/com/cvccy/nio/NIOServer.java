package com.cvccy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author xhf
 * @version V1.0
 * @date 2020/8/6 8:36 下午
 * @description nio服务端
 */

public class NIOServer {

    public static void main(String[] args) throws IOException {
        // 创建一个在本地端口进行监听的服务Socket通道.并设置为非阻塞方式
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); //可当成server端
        //必须配置为非阻塞才能往selector上注册，否则会报错，selector模式本身就是非阻塞模式
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 创建一个选择器selector
        Selector selector = Selector.open();
        // 把ServerSocketChannel注册到selector上，并且selector对客户端accept连接操作感兴趣
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//虽然已经注册到了Selector中，但是没有客户端连接，处于未就绪状态，因此无法被select(); 注册信息会存放于Keys()中

        while (true) {
            System.out.println("等待事件发生。。");
            // 轮询监听channel里的key，select是阻塞的，accept()也是阻塞的
            int select = selector.select(); //阻塞，一旦有连接，连接放到了selectedKey集合中

            System.out.println("有事件发生了。。");
            // 有客户端请求，被轮询监听到
            Iterator<SelectionKey> it = selector.selectedKeys().iterator(); //每个channal注册到selector中都会与一个selectedKey绑定，这里只返回已准备好的channel
            /**
             * 移除了selectedKeys中的SelectionKey不代表移除了selector中的channel信息(这点很重要)，
             * 注册过的channel信息会以SelectionKey的形式存储在selector.keys()中，
             * 也就是说每次select()后的selectedKeys迭代器中是不能还有成员的，
             * 但keys()中的成员是不会被删除的(以此来记录channel信息)。
             */
            while (it.hasNext()) {
                SelectionKey key = it.next();
                //删除本次已处理的key，防止下次select重复处理
                it.remove();//Selector不会自己删除处理过的已就绪的某节点的selectionKey信息，需要删除，不然下次处理依旧存在
                handle(key); //拿到key处理 //redis再这里若用线程池，并发操作速度可能更快，但会有各种并发问题
            }
        }
    }


    private static void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("有客户端连接事件发生了。。");
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel(); // 实际上可当作SelectionKey==Channel
            //NIO非阻塞体现：此处accept方法是阻塞的，但是这里因为是发生了连接事件，所以这个方法会马上执行完，不会阻塞
            //处理完连接请求不会继续等待客户端的数据发送
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            //通过Selector监听Channel时对读事件感兴趣
            sc.register(key.selector(), SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            System.out.println("有客户端数据可读事件发生了。。");
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //NIO非阻塞体现:首先read方法不会阻塞，其次这种事件响应模型，当调用到read方法时肯定是发生了客户端发送数据的事件
            int len = sc.read(buffer);
            if (len != -1) {
                System.out.println("读取到客户端发送的数据：" + new String(buffer.array(), 0, len));
            }
            ByteBuffer bufferToWrite = ByteBuffer.wrap("HelloClient".getBytes());
            sc.write(bufferToWrite);
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
            SocketChannel sc = (SocketChannel) key.channel();
            System.out.println("write事件");
            // NIO事件触发是水平触发
            // 使用Java的NIO编程的时候，在没有数据可以往外写的时候要取消写事件，
            // 在有数据往外写的时候再注册写事件
            key.interestOps(SelectionKey.OP_READ);
            //sc.close();
        }
    }
}
