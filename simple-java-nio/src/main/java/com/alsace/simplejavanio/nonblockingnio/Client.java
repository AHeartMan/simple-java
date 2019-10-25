package com.alsace.simplejavanio.nonblockingnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/25 0025
 */
public class Client {

    public void client() throws IOException {
        //获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));

        //切换到非阻塞模式
        socketChannel.configureBlocking(false);

        //获取指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //发送数据到服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            buffer.put((new Date().toString() + "\n" + str).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();

            if ("88".equals(str)){
                break;
            }
        }

        //关闭通道
        socketChannel.close();
    }
}
