package com.alsace.simplejavanio.nonblockingnio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/25 0025
 */
public class Server {

    public void server() throws Exception {
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //切换到非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8899));

        //获取选择器
        Selector selector = Selector.open();

        //将通道注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //轮询获取已经准备就绪的事件
        while (selector.select() > 0) {
            //获取当前选择器上已准备就绪的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //准备就绪的事件
                SelectionKey selectionKey = iterator.next();

                //判断是什么事件
                if (selectionKey.isAcceptable()) {
                    //若为连接事件，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //读取数据
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = channel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
            }
            //取消选择键 selectionKey
            iterator.remove();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.server();
    }
}
