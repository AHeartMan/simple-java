package com.alsace.simplejavanetty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static void main(String[] args) throws Exception {
        // serverSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // selector
        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));  // 绑定端口
        serverSocketChannel.configureBlocking(false); // 设置为非阻塞

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册为连接事件

        while (true) {
            if (selector.select(3000) == 0) {  // 没有连接，就在这循环等待
                System.out.println("等待连接中...");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {  // 连接事件
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024)); // 注册到selector,并设置为读事件
                }
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();  // 通过key获取到对应的 socketChannel
                    ByteBuffer buffer = (ByteBuffer) key.attachment();   // 获取 socketChannel 关联的 buffer
                    socketChannel.read(buffer);   // 读取信息

                    System.out.println(new String(buffer.array()));
                }
                iterator.remove();
            }
        }
    }
}
