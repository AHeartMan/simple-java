package com.alsace.simplejavanetty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                // 连接需要事件，如果没有连接成功，在这可以做其他事情，不会阻塞
                System.out.println("连接中...");
            }
        }
        String str = "hello world";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}
