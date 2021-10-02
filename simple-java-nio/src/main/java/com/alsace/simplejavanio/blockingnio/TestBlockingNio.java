package com.alsace.simplejavanio.blockingnio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * <p>
 * 使用nio完成网络通信的3大核心
 * 1.通道（Channel）：负责连接
 * java.nio.channels.Channel 接口：
 * |--SelectableChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * |--Pipe.SinkChannel
 * |--Pipe.SourceChannel
 * <p>
 * 2.缓冲区（Buffer）：负责数据存取
 * <p>
 * 3.选择器（Selector）：是SelectableChannel的多路复用器，用于监控SelectableChannel的IO状况
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/24 0024
 */
public class TestBlockingNio {

    /**
     * 服务端
     *
     * @throws Exception
     */
    public void server() throws Exception {
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel fileChannel = FileChannel.open(Paths.get("D:/work_space/simple-java/simple-java-nio/pic7.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8899));

        //获取客户端的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        //分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //接受数据
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        //返回响应
        buffer.put("receive success".getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        //关闭通道
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }

    public void client() throws Exception {
        //获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        FileChannel fileChannel = FileChannel.open(Paths.get("D:/work_space/simple-java/simple-java-nio/pic1.jpg"), StandardOpenOption.READ);

        //获取指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读取本地文件，发送到服务端
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //在不关闭通道的情况下关闭连接以进行写入
        socketChannel.shutdownOutput();

        //接受响应
        int len;
        while ((len = socketChannel.read(buffer)) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();
        }

        //关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    public static void main(String[] args) throws Exception {
        TestBlockingNio blockingNio = new TestBlockingNio();
        blockingNio.server();
    }

}
